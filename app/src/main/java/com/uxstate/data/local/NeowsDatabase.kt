package com.uxstate.data.local

import androidx.room.RoomDatabase

abstract class NeowsDatabase : RoomDatabase(){

abstract val dao:NeowsDao
}