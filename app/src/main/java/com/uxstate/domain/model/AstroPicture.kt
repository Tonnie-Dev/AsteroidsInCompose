package com.uxstate.domain.model

import kotlinx.parcelize.Parcelize

@Parcelize
data class AstroPicture(
    val title: String,
    val explanation: String,
    val mediaType: String,
    val url: String
)
