package com.uxstate.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface NeowsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNeows(neows:List<NeowsEntity>)

}