package com.delivero.customer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.delivero.customer.R
import com.delivero.customer.databinding.DriverItemBinding
import com.delivero.customer.helpers.MapUtils
import com.delivero.customer.interfaces.AdapterListener
import com.delivero.customer.interfaces.CompleteListener
import com.delivero.customer.models.User
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.textview.MaterialTextView

class RiderListAdapter(
    private var riderList:ArrayList<User>, private var listener:AdapterListener,
    private var destLatLng: LatLng, var icon:String) :
RecyclerView.Adapter<RiderListAdapter.RiderHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiderHolder {
        return RiderHolder(DriverItemBinding.inflate(LayoutInflater.from(parent.context),null,false))
    }

    override fun onBindViewHolder(holder: RiderHolder, position: Int) {
        val rider=riderList[position]
        holder.binding.riderName.text=rider.userName
        holder.binding.cost.text="Ksh.0"
        holder.binding.numberPlate.text=rider.capacity
        holder.binding.selectRider.setOnClickListener {
            listener.onUser(it,rider)
        }
        if (rider.profilePicture==""){
            holder.binding.riderLogo.load(icon){
                crossfade(true)
                placeholder(R.drawable.loading)
            }
        }else{
            holder.binding.riderLogo.load(rider.profilePicture){
                crossfade(true)
                placeholder(R.drawable.loading)
            }
        }
        holder.binding.root.setOnClickListener {
            listener.onUser(it,rider)
        }
        fetchTime(holder.binding.arrivalTime,LatLng(rider.latitude,rider.longitude))
    }

    private fun fetchTime(arrivalTime: MaterialTextView, latLng: LatLng) {
        MapUtils.fetchTime(latLng,destLatLng,object :CompleteListener{
            override fun onDuration(duration: String) {
                arrivalTime.text=duration
            }
        })
    }

    override fun getItemCount(): Int {
        return riderList.size
    }

    inner class RiderHolder(var binding:DriverItemBinding):RecyclerView.ViewHolder(binding.root)
}