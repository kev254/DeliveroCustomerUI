package com.delivero.customer.home

import android.Manifest
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.delivero.customer.BuildConfig
import com.delivero.customer.R
import com.delivero.customer.adapters.AutoCompleteAdapter
import com.delivero.customer.databinding.FragmentPickupAndDropOffBinding
import com.delivero.customer.helpers.MapUtils
import com.delivero.customer.helpers.Utils
import com.delivero.customer.helpers.toast
import com.delivero.customer.interfaces.CompleteListener
import com.delivero.customer.models.MyLocation
import com.delivero.customer.models.VehicleTypes
import com.delivero.customer.service.MyLocationService
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jianastrero.capiche.iNeed
import com.serhatleventyavas.ripplemapview.RippleMapView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import kotlin.math.roundToInt

class PickupAndDropOffFragment:Fragment(), OnMapReadyCallback {
    private var rippleView: RippleMapView? = null
    private var grayPolyline: Polyline? = null
    private var destinationLatLng: LatLng? = null
    private var destinationMarker: Marker? = null
    private var originMarker: Marker? = null
    private var myLocationMarker: Marker?=null
    private var pickupLatLng: LatLng? = null
    private var flag=0
    private var defaultLocation: LatLng? = null
    private var googleMap: GoogleMap?=null
    private lateinit var destinationAdapter: AutoCompleteAdapter
    private lateinit var pickupAdapter: AutoCompleteAdapter
    private lateinit var placesClient: PlacesClient
    private lateinit var bounds: LatLngBounds.Builder
    private lateinit var mapView: MapView
    private lateinit var binding:FragmentPickupAndDropOffBinding
    private lateinit var vehicleTypes: VehicleTypes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vehicleTypes=PickupAndDropOffFragmentArgs.fromBundle(requireArguments()).vehicleType
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentPickupAndDropOffBinding.inflate(inflater, container, false)

        mapView = binding.mapView
        bounds = LatLngBounds.builder()
        if (!Places.isInitialized()) {
            Places.initialize(requireActivity().applicationContext, BuildConfig.GOOGLE_API_KEY)
        }

        placesClient = Places.createClient(requireActivity())

        binding.pickupValue.threshold = 1
        binding.destinationValue.threshold = 1

        pickupAdapter = AutoCompleteAdapter(requireContext(), placesClient)
        destinationAdapter = AutoCompleteAdapter(requireContext(), placesClient)
        binding.btnNext.setOnClickListener {
            if (pickupLatLng!=null && destinationLatLng!=null){
                findNavController().navigate(PickupAndDropOffFragmentDirections.actionPickupAndDropOffFragmentToPackageDetailsFragment(vehicleTypes,
                    pickupLatLng!!.latitude.toFloat(), pickupLatLng!!.longitude.toFloat(), destinationLatLng!!.latitude.toFloat(), destinationLatLng!!.longitude.toFloat()
                ,binding.pickupValue.text.toString(),binding.destinationValue.text.toString()))
            }else{
                val utils=Utils()
                utils.showMessageDialog(requireContext(),"You missed something","Please provide the pickup and destination points")
            }
        }

        if (vehicleTypes.purpose=="Breakdown" ||vehicleTypes.purpose=="Emergency"){
            binding.pickupValue.hint="Your location"
            binding.destinationValue.hint="Your destination"
        }

        setUpMap(savedInstanceState)

        return binding.root
    }

    private fun setUpMap(savedInstanceState: Bundle?) {
        mapView.onCreate(savedInstanceState)
        mapView.onResume()
        // needed to get the map to display immediately
        try {
            MapsInitializer.initialize(
                requireActivity().applicationContext
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
        mapView.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        this.googleMap = p0
        defaultLocation = LatLng(0.0236, 37.9062)
        googleMap?.setIndoorEnabled(true)
        googleMap?.isTrafficEnabled=true
        showDefaultLocationOnMap(defaultLocation!!)
       rippleView= this.googleMap?.let {
            RippleMapView.Builder(requireContext(), it)
                .fillColor(ContextCompat.getColor(requireContext(),R.color.secondary))
                .strokeColor(ContextCompat.getColor(requireContext(),R.color.secondary))
                .latLng(LatLng(41.009146, 29.034022))
                .numberOfRipples(3)
                .build()
        }
        rippleView?.startRippleMapAnimation()
        checkLocationPermission()
    }

    private fun checkLocationPermission() {
        if (isAdded) {
            requireActivity().iNeed(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                onGranted = {
                    checkGpsEnabled()
                    checkNotificationPermissions()
                },
                onDenied = {
                    MaterialAlertDialogBuilder(requireContext())
                        .setBackground(
                            ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.bg_white_rounded_10
                            )
                        )
                        .setTitle("Please allow location permissions")
                        .setMessage("We need your location permission in order to get you the nearest rider.")
                        .setPositiveButton("Allow in settings") { dialog, _ ->
                            dialog?.dismiss()
                            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            val uri = Uri.fromParts("package", requireContext().packageName, null)
                            intent.data = uri
                            startActivity(intent)
                        }.setNegativeButton("Cancel") { dialog, _ ->
                            dialog?.dismiss()
                        }.create()
                        .show()
                    checkNotificationPermissions()

                })
        }
    }

    private fun addOriginMarkerAndGet(latLng: LatLng): Marker? {
        originMarker?.remove()
        val bitmapDescriptor =
            BitmapDescriptorFactory.fromBitmap(MapUtils.getOriginMarkerBitmap(requireContext()))
        return googleMap?.addMarker(
            MarkerOptions().position(latLng).flat(true).icon(bitmapDescriptor)
        )
    }

    private fun addDestinationMarkerAndGet(latLng: LatLng): Marker? {
        destinationMarker?.remove()
        val bitmapDescriptor =
            BitmapDescriptorFactory.fromBitmap(
                MapUtils.getDestinationMarkerBitmap(
                    requireContext()
                )
            )
        return googleMap?.addMarker(
            MarkerOptions().position(latLng).flat(true).icon(bitmapDescriptor)
        )
    }


    private fun setUpPickupAndDestinationAdapters() {
        binding.pickupValue.setOnItemClickListener { _, _, position, _ ->
            try {
                binding.progress.show()
                val item = pickupAdapter.getItem(position)
                val placeID: String = item.placeId

//                To specify which data types to return, pass an array of Place.Fields in your FetchPlaceRequest
//                Use only those fields which are required.
                val placeFields = listOf(
                    Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG
                )
                val request: FetchPlaceRequest? = FetchPlaceRequest.builder(placeID, placeFields)
                    .build()
                if (request != null) {
                    placesClient.fetchPlace(request).addOnSuccessListener { task ->
                        binding.progress.hide()

                        pickupLatLng =
                            LatLng(task.place.latLng!!.latitude, task.place.latLng!!.longitude)
                       /* trip.fromName = task.place.name!!
                        fromAddress = task.place.address!!
                       */
                        rippleView?.withLatLng(pickupLatLng)
                        if (rippleView?.isAnimationRunning!=true){
                            rippleView?.startRippleMapAnimation()
                        }
                        originMarker = addOriginMarkerAndGet(pickupLatLng!!)
                        drawRoute()
                    }
                        .addOnFailureListener { e ->
                            binding.progress.hide()
                            e.printStackTrace()
                            requireActivity().toast("Failed to retrieve location")
                        }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.destinationValue.setOnItemClickListener { _, _, position, _ ->
            try {
                binding.progress.show()
                val item = destinationAdapter.getItem(position)
                val placeID: String = item.placeId

//                To specify which data types to return, pass an array of Place.Fields in your FetchPlaceRequest
//                Use only those fields which are required.
                val placeFields = listOf(
                    Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG
                )
                val request: FetchPlaceRequest? = FetchPlaceRequest.builder(placeID, placeFields)
                    .build()

                request?.let {
                    placesClient.fetchPlace(it).addOnSuccessListener { task ->
                        binding.progress.hide()
                        destinationLatLng =
                            LatLng(task.place.latLng!!.latitude, task.place.latLng!!.longitude)
                       /* trip.toName = task.place.name!!
                        showApproximateSummary()*/
                        destinationMarker=addDestinationMarkerAndGet(destinationLatLng!!)
                        drawRoute()

                    }
                        .addOnFailureListener { e ->
                            binding.progress.hide()
                            e.printStackTrace()
                            requireActivity().toast("Failed to retrieve location")
                        }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.pickupValue.setAdapter(pickupAdapter)
        binding.destinationValue.setAdapter(destinationAdapter)


    }

    private fun shoMyLocation(latLng: LatLng) {
        myLocationMarker = googleMap?.addMarker(
            MarkerOptions().icon(
                BitmapDescriptorFactory.fromBitmap(
                    MapUtils.getMyMarkerBitmap(
                        requireContext()
                    )
                )
            )
                .position(latLng)
                .title("Your Position")
        )
        animateCamera(latLng)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun myLocationReady(myLocation: MyLocation) {
        if(!isAdded){
            return
        }
        defaultLocation = LatLng(myLocation.latitude, myLocation.longitude)
        shoMyLocation(defaultLocation!!)
       // getNearbyRiders()
        setUpPickupAndDestinationAdapters()
        pickupLatLng = defaultLocation
        originMarker = addOriginMarkerAndGet(defaultLocation!!)
        rippleView?.withLatLng(defaultLocation)
        MapUtils.reverseGeocode(object : CompleteListener {
            override fun onComplete(value: String) {
                //trip.fromName = value
                binding.pickupValue.setText(value)
                //fromAddress = value
            }
        }, pickupLatLng!!)

    }

    private fun moveCamera(latLng: LatLng) {
        googleMap?.moveCamera(CameraUpdateFactory.newLatLng(latLng))
    }

    private fun animateCamera(latLng: LatLng) {
        val cameraPosition = CameraPosition.Builder().target(latLng).zoom(15.5f).build()
        googleMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    private fun showDefaultLocationOnMap(latLng: LatLng) {
        moveCamera(latLng)
        animateCamera(latLng)
    }

    private fun checkNotificationPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireActivity().iNeed(
                Manifest.permission.POST_NOTIFICATIONS,
                onGranted = {},
                onDenied = {})
        }
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (flag == 1) {
                flag = 0
                checkGpsEnabled()
            }
        }

    private fun checkGpsEnabled() {
        val manager: LocationManager =
            requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && !manager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
            ) && !manager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER) && !manager.isProviderEnabled(
                LocationManager.FUSED_PROVIDER
            )
        ) {
            flag = 1
            resultLauncher.launch(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            Toast.makeText(requireContext(), "Please turn on GPS", Toast.LENGTH_LONG).show()
        } else {
            startLocationService()
        }

    }
    private fun startLocationService() {
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this)
        }
        val intent = Intent(requireContext(), MyLocationService::class.java)
        requireActivity().startService(intent)
    }

    private fun drawRoute() {
        if (originMarker == null || destinationMarker == null) {
            return
        }
        MapUtils.getRoutes(pickupLatLng!!, destinationLatLng!!, object : CompleteListener {
            override fun onPoints(decodePoly: List<LatLng>, distance: String) {

                grayPolyline?.remove()
                val poly = PolylineOptions()

                if (isAdded) {
                    poly.color(
                        ContextCompat.getColor(
                            requireContext(),
                            com.google.android.libraries.places.R.color.quantum_bluegrey800
                        )
                    )
                }
                poly.addAll(decodePoly)
                grayPolyline = googleMap?.addPolyline(poly)
                animateToBounds()
                if (rippleView?.isAnimationRunning!=true){
                    rippleView?.startRippleMapAnimation()
                }
            }
        })
    }


    private fun animateToBounds() {
        bounds = LatLngBounds.builder()
        bounds.include(pickupLatLng!!)
        bounds.include(destinationLatLng!!)
        val height = resources.displayMetrics.heightPixels.times(0.8).roundToInt()
        val width = resources.displayMetrics.widthPixels
        val padding = width.times(0.30).roundToInt()
        val update = CameraUpdateFactory.newLatLngBounds(bounds.build(), width, height, padding)
        googleMap?.animateCamera(update)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()

    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()

    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }


}