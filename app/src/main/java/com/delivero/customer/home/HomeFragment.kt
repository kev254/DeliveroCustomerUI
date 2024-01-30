package com.delivero.customer.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import coil.load
import com.delivero.customer.R
import com.delivero.customer.adapters.TabsAdapter
import com.delivero.customer.databinding.FragmentHomeBinding
import com.delivero.customer.helpers.Preference
import com.delivero.customer.models.User
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment:Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private lateinit var user:User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentHomeBinding.inflate(inflater, container, false)
        val tabNames= arrayOf("Delivery","Breakdown","Emergency")
        val icons= intArrayOf(R.drawable.delivery,R.drawable.breakdown,R.drawable.emergency)
        val adapter=TabsAdapter(requireActivity())
        adapter.addFragment(DeliveryFragment())
        adapter.addFragment(BreakDownFragment())
        adapter.addFragment(EmergencyFragment())

        binding.pager.adapter=adapter
        TabLayoutMediator(binding.tabLayout,binding.pager){
            tab,position->
            tab.text=tabNames[position]
            tab.icon=ContextCompat.getDrawable(requireContext(),icons[position])
        }.attach()
        initUser()
        return binding.root
    }

    private fun initUser() {
        user=Preference.getUser(requireContext())!!
        binding.userName.text="Hi,${user.userName}"
        if (user.profilePicture.isNotEmpty()){
            binding.avatar.load(user.profilePicture){
                crossfade(true)
            }
        }
    }
}