package com.delivero.customer.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabsAdapter(activity:FragmentActivity):FragmentStateAdapter(activity) {
    private var fragments= arrayListOf<Fragment>()
    fun addFragment(fragment: Fragment){
        fragments.add(fragment)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}