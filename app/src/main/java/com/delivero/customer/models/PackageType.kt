package com.delivero.customer.models

import com.google.firebase.firestore.DocumentId
import java.io.Serializable

data class PackageType(@DocumentId var typeId:String="",var typeName:String="",var icon:String=""):Serializable
