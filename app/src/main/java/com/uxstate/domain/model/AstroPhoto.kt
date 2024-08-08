package com.uxstate.domain.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//This is parcelable to allow it to be moved around
@Parcelize
data class AstroPhoto(
    val id:String,
    val title: String,
    val explanation: String,
    val mediaType: String,
    val url: String,
    val timeStamp:Long,
    val isFavorite:Boolean
) : Parcelable
