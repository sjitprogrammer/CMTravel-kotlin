package com.example.cmtravel.screens

import android.os.Parcelable
import com.google.firebase.firestore.GeoPoint
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Trips(
    val id: String,
    val title:String,
    val description:String,
    val imageUrl:String,
//    val location: GeoPoint,
    val price: Int
):Parcelable