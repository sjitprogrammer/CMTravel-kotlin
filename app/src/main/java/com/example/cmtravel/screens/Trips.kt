package com.example.cmtravel.screens

import android.location.Location
import com.google.firebase.firestore.GeoPoint

data class Trips(
    val title:String,
    val description:String,
    val imageUrl:String,
    val location: GeoPoint,
    val price: Int
)