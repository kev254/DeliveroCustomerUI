package com.delivero.customer.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.delivero.customer.adapters.VehicleTypesAdapter
import com.delivero.customer.databinding.FragmentHomeBinding
import com.delivero.customer.helpers.ItemDecoration
import com.delivero.customer.helpers.Preference
import com.delivero.customer.helpers.toast
import com.delivero.customer.interfaces.AdapterListener
import com.delivero.customer.interfaces.Collections
import com.delivero.customer.models.User
import com.delivero.customer.models.VehicleTypes
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment:Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private lateinit var user:User
    private  var vehicleTypes= arrayListOf<VehicleTypes>()
    private lateinit var adapter: VehicleTypesAdapter

    private  var vehicleTypes2= arrayListOf<VehicleTypes>()
    private lateinit var adapter2: VehicleTypesAdapter

    private  var vehicleTypes3= arrayListOf<VehicleTypes>()
    private lateinit var adapter3: VehicleTypesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentHomeBinding.inflate(inflater, container, false)

        binding.vehicleListRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.vehicleListRecycler.setHasFixedSize(true)
        binding.vehicleListRecycler.addItemDecoration(ItemDecoration())
        adapter= VehicleTypesAdapter(requireContext(),vehicleTypes,object : AdapterListener {
            override fun onVehicleType(view: View, type: VehicleTypes) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPickupAndDropOffFragment(type))
            }
        })
        binding.vehicleListRecycler.adapter=adapter


        binding.vehicleListRecycler2.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.vehicleListRecycler2.setHasFixedSize(true)
        binding.vehicleListRecycler2.addItemDecoration(ItemDecoration())
        adapter2 = VehicleTypesAdapter(requireContext(), vehicleTypes2, object : AdapterListener {
            override fun onVehicleType(view: View, type: VehicleTypes) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPickupAndDropOffFragment(type))
            }
        })
        binding.vehicleListRecycler2.adapter = adapter2


        binding.vehicleListRecycler3.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.vehicleListRecycler3.setHasFixedSize(true)
        binding.vehicleListRecycler3.addItemDecoration(ItemDecoration())
        adapter3 = VehicleTypesAdapter(requireContext(), vehicleTypes3, object : AdapterListener {
            override fun onVehicleType(view: View, type: VehicleTypes) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPickupAndDropOffFragment(type))
            }
        })
        binding.vehicleListRecycler3.adapter = adapter3


        initUser()
        populateVehicleTypes()
        populateBreakdownVehicleTypes()
        populateEmergencyVehicleTypes()
        return binding.root
    }

    private fun initUser() {
        user=Preference.getUser(requireContext())!!
        binding.userName.text="Hi, ${user.userName}"
        if (user.profilePicture.isNotEmpty()){
            binding.avatar.load(user.profilePicture){
                crossfade(true)
            }
        }
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

    private fun populateBreakdownVehicleTypes() {
        binding.progress2.show()
        Firebase.firestore.collection(Collections.TYPES)
            .whereEqualTo("purpose","Breakdown")
            .get()
            .addOnSuccessListener {
                binding.progress2.hide()
                if (it.isEmpty){
                    return@addOnSuccessListener
                }
                vehicleTypes2.clear()
                vehicleTypes2.addAll(it.toObjects(VehicleTypes::class.java))
                adapter2.notifyDataSetChanged()
            }.addOnFailureListener {
                binding.progress2.hide()
                requireContext().toast("Could not get available vehicle types, Please try again later")
            }


    }

    private fun populateEmergencyVehicleTypes() {
        binding.progress3.show()
        Firebase.firestore.collection(Collections.TYPES)
            .whereEqualTo("purpose","Emergency")
            .get()
            .addOnSuccessListener {
                binding.progress3.hide()
                if (it.isEmpty){
                    return@addOnSuccessListener
                }
                vehicleTypes3.clear()
                vehicleTypes3.addAll(it.toObjects(VehicleTypes::class.java))
                adapter3.notifyDataSetChanged()
            }.addOnFailureListener {
                binding.progress3.hide()
                requireContext().toast("Could not get available vehicle types, Please try again later")
            }


    }
}