package com.uxstate.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//This is parcelable to allow it to be moved around
@Parcelize
data class AstroPhoto(
    val date:String,
    val title: String,
    val explanation: String,
    val mediaType: String,
    val url: String,
    var isFavorite:Boolean
) : Parcelable
