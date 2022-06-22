package com.uxstate.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AstroPhotoEntity::class], version = 3)
abstract class AstroDatabase : RoomDatabase(){

abstract val dao:AstroDao

companion object{

    const val DB_NAME = "AstroDatabase"
}
}