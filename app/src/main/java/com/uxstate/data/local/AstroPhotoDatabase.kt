package com.uxstate.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AstroPhotoEntity::class], version = 1)
abstract class AstroPhotoDatabase : RoomDatabase(){

abstract val dao:AstroDao

companion object{

    const val DB_NAME = "NeowsDatabase"
}
}