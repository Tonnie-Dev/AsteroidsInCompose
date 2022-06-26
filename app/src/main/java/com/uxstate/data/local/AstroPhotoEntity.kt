package com.uxstate.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AstroPhotoEntity(

    @PrimaryKey
    val id: String,
    val title: String,
    val mediaType:String,
    val explanation: String,
    val url: String,
    var isFavorite:Boolean
)
