package com.uxstate.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavPhotoEntity(  @PrimaryKey
                            val id: String,
                            val timeStamp:Long,
                            val title: String,
                            val mediaType:String,
                            val explanation: String,
                            val url: String,
                            val isFavorite:Boolean)
