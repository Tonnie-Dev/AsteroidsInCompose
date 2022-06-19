package com.uxstate.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AstroPhotoEntity(

    @PrimaryKey
    val date: String,
    val title: String,
    val explanation: String,
    val url: String
)
