package com.uxstate.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AstroPhoto(
    val date:String,
    val title: String,
    val explanation: String,
    val mediaType: String,
    val url: String,
    val isFavorite:Boolean
) : Parcelable
