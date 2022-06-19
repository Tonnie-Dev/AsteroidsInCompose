package com.uxstate.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AstroPhoto(
    val title: String,
    val explanation: String,
    val mediaType: String,
    val url: String
) : Parcelable
