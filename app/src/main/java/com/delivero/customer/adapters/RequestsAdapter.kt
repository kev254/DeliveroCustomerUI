package com.delivero.customer.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.delivero.customer.databinding.RequestItemBinding
import com.delivero.customer.interfaces.AdapterListener
import com.delivero.customer.models.Request
import java.text.SimpleDateFormat
import java.util.Locale

class RequestsAdapter(var context: Context, private var requests:ArrayList<Request>, private var listener: AdapterListener):
RecyclerView.Adapter<RequestsAdapter.RequestHolder>(){
val dateFormat by lazy { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestHolder {
        return RequestHolder(RequestItemBinding.inflate(LayoutInflater.from(context),null,false))
    }

    override fun onBindViewHolder(holder: RequestHolder, position: Int) {
        val request=requests[position]
        holder.binding.to.text=request.destination
        holder.binding.from.text=request.pickup
        holder.binding.date.text=dateFormat.format(request.createdAt)
        holder.binding.cost.text="Ksh.${request.cost}"
        holder.binding.trackingNumber.text=request.trackingNumber.toString()
        holder.binding.requestType.text=request.requestType
        holder.binding.details.setOnClickListener {
            listener.onRequestDetails(request)
        }
    }

    override fun getItemCount(): Int {
        return requests.size
    }

    inner class RequestHolder(var binding:RequestItemBinding):RecyclerView.ViewHolder(binding.root)
}