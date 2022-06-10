package com.uxstate.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NeowsEntity::class], version = 1)
abstract class NeowsDatabase : RoomDatabase(){

abstract val dao:NeowsDao

companion object{

    const val DB_NAME = "NeowsDatabase"
}
}