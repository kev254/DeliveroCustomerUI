package com.delivero.customer.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.delivero.customer.R
import com.delivero.customer.databinding.VehicleItemBinding
import com.delivero.customer.interfaces.AdapterListener
import com.delivero.customer.interfaces.Collections
import com.delivero.customer.models.VehicleTypes
import com.google.android.material.textview.MaterialTextView
import com.google.firebase.firestore.AggregateSource
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class VehicleTypesAdapter(var context:Context,var types:ArrayList<VehicleTypes>,var listener: AdapterListener):RecyclerView.Adapter<VehicleTypesAdapter.TypeHolder>() {
    override fun getItemCount(): Int {
        return types.size
    }

    override fun onBindViewHolder(holder: TypeHolder, position: Int) {
val type=types[position]
        holder.binding.typeName.text=type.typeName
        holder.binding.vehicleItem.load(type.icon){
            crossfade(true)
            placeholder(R.drawable.loading)
        }
        holder.binding.select.setOnClickListener {
            listener.onVehicleType(it,type)
        }
        holder.binding.root.setOnClickListener {
            listener.onVehicleType(it,type)
        }
        getAvailableTypeCount(holder.binding.typeCount,type.typeName)
    }

    private fun getAvailableTypeCount(typeCount: MaterialTextView, typeName: String) {
        Firebase.firestore.collection(Collections.USERS)
            .whereArrayContains("roles",typeName)
            .count()
            .get(AggregateSource.SERVER)
            .addOnSuccessListener {
                typeCount.text="(${it.count} Available)";
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VehicleTypesAdapter.TypeHolder {
        return TypeHolder(VehicleItemBinding.inflate(LayoutInflater.from(context),null,false))
    }

  inner class TypeHolder(var binding: VehicleItemBinding):RecyclerView.ViewHolder(binding.root)
}