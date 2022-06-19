package com.uxstate.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AstroPhotoDTO(
    @Json(name = "title")
    val title: String,
    @Json(name = "explanation")
    val explanation: String,
    @Json(name = "media_type")
    val mediaType: String,
    @Json(name = "url")
    val url: String
)