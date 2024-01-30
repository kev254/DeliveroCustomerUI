package com.delivero.customer.models

import com.google.firebase.firestore.DocumentId
import java.io.Serializable

data class ServiceType(
    var icon:Int,
    var typeName:String=""):Serializable
