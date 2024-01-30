package com.delivero.customer.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.delivero.customer.R
import com.delivero.customer.adapters.VehicleTypesAdapter
import com.delivero.customer.databinding.FragmentDeliveryBinding
import com.delivero.customer.databinding.FragmentHomeBinding
import com.delivero.customer.helpers.ItemDecoration
import com.delivero.customer.helpers.toast
import com.delivero.customer.interfaces.AdapterListener
import com.delivero.customer.interfaces.Collections
import com.delivero.customer.models.VehicleTypes
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DeliveryFragment:Fragment() {
    private lateinit var binding:FragmentDeliveryBinding
    private  var vehicleTypes= arrayListOf<VehicleTypes>()
    private lateinit var adapter:VehicleTypesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentDeliveryBinding.inflate(inflater, container, false)
        binding.vehicleListRecycler.layoutManager=GridLayoutManager(requireContext(),2)
        binding.vehicleListRecycler.setHasFixedSize(true)
        binding.vehicleListRecycler.addItemDecoration(ItemDecoration())
        adapter= VehicleTypesAdapter(requireContext(),vehicleTypes,object :AdapterListener{
            override fun onVehicleType(view: View, type: VehicleTypes) {
              findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPickupAndDropOffFragment(type))
            }
        })
        binding.vehicleListRecycler.adapter=adapter
        populateVehicleTypes()

        return binding.root
    }

    private fun populateVehicleTypes() {
        binding.progress.show()
        Firebase.firestore.collection(Collections.TYPES)
            .whereEqualTo("purpose","Delivery")
            .get()
            .addOnSuccessListener {
                binding.progress.hide()
                if (it.isEmpty){
                   return@addOnSuccessListener
                }
                vehicleTypes.clear()
                vehicleTypes.addAll(it.toObjects(VehicleTypes::class.java))
                adapter.notifyDataSetChanged()
            }.addOnFailureListener {
                binding.progress.hide()
                requireContext().toast("Could not get available vehicle types, Please try again later")
            }


    }
}