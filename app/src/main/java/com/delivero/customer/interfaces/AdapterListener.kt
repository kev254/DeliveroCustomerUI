package com.delivero.customer.interfaces

import android.view.View
import com.delivero.customer.models.Request
import com.delivero.customer.models.User
import com.delivero.customer.models.VehicleTypes

interface AdapterListener {
    fun onVehicleType(view:View,type:VehicleTypes){

    }

    fun onUser(view: View, rider: User) {

    }

    fun onRequestDetails(request: Request) {

    }
}