package com.uxstate.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NeowsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNeows(neows:List<NeowsEntity>)

    @Query("DELETE from neowsentity")
    suspend fun clearNeows()

}