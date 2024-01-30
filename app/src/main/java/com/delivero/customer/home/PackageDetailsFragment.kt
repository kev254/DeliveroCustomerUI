package com.delivero.customer.home

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.delivero.customer.R
import com.delivero.customer.databinding.PackageDetailsFragmentBinding
import com.delivero.customer.helpers.Utils
import com.delivero.customer.helpers.hide
import com.delivero.customer.models.PackageType
import com.delivero.customer.models.Request
import com.delivero.customer.models.VehicleTypes
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class PackageDetailsFragment:Fragment(){
    private lateinit var binding:PackageDetailsFragmentBinding
    private var request=Request()
    private var utils=Utils()
    private lateinit var vehicleTypes: VehicleTypes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vehicleTypes=PackageDetailsFragmentArgs.fromBundle(requireArguments()).vehicleType
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=PackageDetailsFragmentBinding.inflate(inflater, container, false)
binding.pickupType.setOnClickListener {
    getackageType()
}
        binding.btnNext.setOnClickListener {
            getInput()
        }
        binding.pickupDate.setOnClickListener {
            showDateInput()
        }
        binding.pickupTime.setOnClickListener {
            showTimeInput()
        }
        if (vehicleTypes.purpose=="Breakdown"){
            binding.hideFor.hide()
            binding.descriptionLayout.hint="Describe your situation"
        }
        if (vehicleTypes.purpose=="Emergency"){
            binding.hideFor.hide()
            binding.descriptionLayout.hint="Describe your emergency"
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (isAdded){
            if (vehicleTypes.purpose=="Breakdown"){
                (requireActivity() as AppCompatActivity).supportActionBar?.title="Service Description"
            }
            if (vehicleTypes.purpose=="Emergency"){
                (requireActivity() as AppCompatActivity).supportActionBar?.title="Emergency Description"
            }
        }
    }

    private fun getInput() {
        val description=binding.packageDescription.text.toString()
        val weight=binding.weightInput.text.toString()
        val type=binding.pickupType.text.toString()
        val pickupDate=binding.pickupDate.text.toString()
        val pickupTime=binding.pickupTime.text.toString()
        if (description.isEmpty()){
            utils.showMessageDialog(requireContext(),"You missed something","Please provide the package description")
            return
        }
        if (vehicleTypes.purpose=="Delivery"){
            if (weight.isEmpty()){
                utils.showMessageDialog(requireContext(),"You missed something","Please provide the package weight")
                return
            }
            if (type.isEmpty()){
                utils.showMessageDialog(requireContext(),"You missed something","Please select the package type")
                return
            }
            if (pickupDate.isEmpty()){
                utils.showMessageDialog(requireContext(),"You missed something","Please provide the pickup date")
                return
            }
            if (pickupTime.isEmpty()){
                utils.showMessageDialog(requireContext(),"You missed something","Please provide the pickup time")
                return
            }

            try {
                val date=SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(pickupDate)
                request.pickupDate=date!!;
            }catch (e:Exception){
                utils.showMessageDialog(requireContext(),"You missed something","The pickup date provided is invalid")
                return
            }
            try {
                val d=Calendar.getInstance()
                d.set(Calendar.HOUR_OF_DAY,pickupTime.split(":").first().toInt())
                d.set(Calendar.MINUTE,pickupTime.split(":").last().toInt())
                request.pickupTime=d.time
            }catch (e:Exception){
                utils.showMessageDialog(requireContext(),"You missed something","The pickup time provided is invalid")
                return
            }
        }



        request.description=description
        request.customerId=Firebase.auth.currentUser!!.uid
        request.status="Pending";

        if (vehicleTypes.purpose=="Delivery") {
            request.weight = weight.toDouble()
            request.pickupType = type
        }
        request.vehicleType=vehicleTypes.typeName
        request.requestType=vehicleTypes.purpose
        request.latitude=PackageDetailsFragmentArgs.fromBundle(requireArguments()).latitude.toDouble()
        request.longitude=PackageDetailsFragmentArgs.fromBundle(requireArguments()).longitude.toDouble()
        request.destinationLatitude=PackageDetailsFragmentArgs.fromBundle(requireArguments()).latitude2.toDouble()
        request.destinationLongitude=PackageDetailsFragmentArgs.fromBundle(requireArguments()).longitude2.toDouble()
        request.icon=vehicleTypes.icon
        request.pickup=PackageDetailsFragmentArgs.fromBundle(requireArguments()).pickup
        request.destination=PackageDetailsFragmentArgs.fromBundle(requireArguments()).destination


        findNavController().navigate(PackageDetailsFragmentDirections.actionPackageDetailsFragmentToRiderSelectionFragment(request))
    }

    private fun showDateInput(){
val calendar=Calendar.getInstance();
        val dialog=DatePickerDialog(requireContext(),
            { _, year, month, dayOfMonth ->
            binding.pickupDate.setText("$dayOfMonth/${month+1}/$year")
            },calendar[Calendar.YEAR],calendar[Calendar.MONTH],calendar[Calendar.DAY_OF_MONTH])
        dialog.show()
    }
    private fun showTimeInput(){
        val calendar=Calendar.getInstance();
        val dialog=TimePickerDialog(requireContext(), {
                                                                                      _,hour,minutes->
                                                      binding.pickupTime.setText("$hour:$minutes")
        },calendar[Calendar.HOUR_OF_DAY],calendar[Calendar.MINUTE],true)
        dialog.show()
    }


    private fun getackageType() {
        Firebase.firestore.collection("PackageTypes")
            .get()
            .addOnSuccessListener { snapshot ->
                if(snapshot.isEmpty){
    return@addOnSuccessListener
}
                val types=snapshot.toObjects(PackageType::class.java).map {
                    it.typeName
                }.toTypedArray()
                MaterialAlertDialogBuilder(requireContext())
                    .setBackground(ContextCompat.getDrawable(requireContext(),R.drawable.bg_white_rounded_10))
                    .setSingleChoiceItems(types,0){
                        dialog,which->
                        binding.pickupType.setText(types[which])
                        dialog?.dismiss()
                    }.create()
                    .show()
            }

    }
}