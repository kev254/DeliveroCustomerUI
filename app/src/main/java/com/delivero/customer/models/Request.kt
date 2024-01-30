package com.delivero.customer.models

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.io.Serializable
import java.util.Date

data class Request(
    @DocumentId
    var requestId:String="",
    var requestType:String="",
                   var vehicleType:String="",
                   var riderId:String="",
                   var customerId:String="",
                   var weight:Double=0.0,
                   var pickupDate:Date= Date(),
                   var pickupTime:Date= Date(),
                   var pickupType:String="",
                   @ServerTimestamp
                   var createdAt:Date=Date(),
    var description:String="",
    var cost:Double=0.0,
    var status:String="",
    var events:List<String> = listOf(),
    var latitude:Double=0.0,
    var longitude:Double=0.0,
    var destinationLatitude:Double=0.0,
    var destinationLongitude:Double=0.0,
    var icon:String="",
                   var pickup:String="",
                   var destination:String="",
    var trackingNumber:Long=System.nanoTime() and 0xfffff
):Serializable
