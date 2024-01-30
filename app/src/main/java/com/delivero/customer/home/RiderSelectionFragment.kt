package com.delivero.customer.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.delivero.customer.MainActivity
import com.delivero.customer.R
import com.delivero.customer.adapters.RiderListAdapter
import com.delivero.customer.databinding.FragmentRiderSelectionBinding
import com.delivero.customer.helpers.ItemDecoration
import com.delivero.customer.helpers.show
import com.delivero.customer.interfaces.AdapterListener
import com.delivero.customer.interfaces.Collections
import com.delivero.customer.models.Request
import com.delivero.customer.models.User
import com.firebase.geofire.GeoFire
import com.firebase.geofire.GeoLocation
import com.firebase.geofire.GeoQueryEventListener
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RiderSelectionFragment: Fragment() {
    private lateinit var binding:FragmentRiderSelectionBinding
    private lateinit var request: Request
    private lateinit var riderList:ArrayList<User>
    private lateinit var adapter:RiderListAdapter
    private var foundRiders= mutableListOf<String>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        request=RiderSelectionFragmentArgs.fromBundle(requireArguments()).request
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding=FragmentRiderSelectionBinding.inflate(inflater, container, false)
        riderList= arrayListOf()
        binding.recycler.setHasFixedSize(true)
        binding.recycler.layoutManager=LinearLayoutManager(requireContext())
        binding.recycler.addItemDecoration(ItemDecoration())
        adapter= RiderListAdapter(riderList,object :AdapterListener{
            override fun onUser(view: View, rider: User) {
                request.riderId=rider.userId
                binding.progress.show()
                Firebase.firestore.collection(Collections.REQUESTS)
                    .add(request)
                    .addOnSuccessListener {
                        binding.progress.hide()
                        MaterialAlertDialogBuilder(requireContext())
                            .setTitle("Request Posted")
                            .setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.bg_white_rounded_10))
                            .setMessage("The driver will contact you shortly. You can also contact the driver on ${rider.phoneNumber}")
                            .setPositiveButton("OK"){
                                dialog,_->
                                dialog?.dismiss()
                            }.setOnDismissListener {
                                startActivity(Intent(requireContext(),MainActivity::class.java))
                            }.create()
                            .show()
                    }
            }
        }, LatLng(request.latitude,request.longitude),request.icon)
        binding.recycler.adapter=adapter
        getRiders()
        return binding.root
    }

    private fun getRiders() {
        binding.progress.show()
        val georef=GeoFire(Firebase.database.getReference(request.vehicleType))
        val geoQuery=georef.queryAtLocation(GeoLocation(request.latitude,request.longitude),2.0)
        geoQuery.addGeoQueryEventListener(object :GeoQueryEventListener{
            override fun onKeyEntered(p0: String?, p1: GeoLocation?) {
                if (p0!=null){
                    foundRiders.add(p0)
                    getRider(p0,p1!!)
                }
            }

            override fun onKeyExited(p0: String?) {
                 foundRiders.remove(p0)
                riderList.removeAll {
                    it.userId==p0
                }
                adapter.notifyDataSetChanged()
            }

            override fun onKeyMoved(p0: String?, p1: GeoLocation?) {

            }

            override fun onGeoQueryReady() {
               binding.progress.hide()
                if (foundRiders.isEmpty()){
                    binding.noData.text="No ${request.vehicleType} drivers Available at the moment"
                    binding.noData.show()
                }
            }

            override fun onGeoQueryError(p0: DatabaseError?) {

            }

        })
    }

    private fun getRider(p0: String,geoLocation: GeoLocation) {
        Firebase.firestore.collection(Collections.USERS)
            .document(p0)
            .get()
            .addOnSuccessListener {
                if (it.exists()){
                    val user=it.toObject(User::class.java)!!
                    user.latitude=geoLocation.latitude
                    user.longitude=geoLocation.longitude
                    riderList.add(user)
                    adapter.notifyDataSetChanged()
                }
            }
    }
}