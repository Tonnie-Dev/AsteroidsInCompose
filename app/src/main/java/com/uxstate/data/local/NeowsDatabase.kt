package com.uxstate.data.local

import androidx.room.RoomDatabase

abstract class NeowsDatabase : RoomDatabase(){

abstract val dao:NeowsDao

companion object{

    const val DB_NAME = "NeowsDatabase"
}
}