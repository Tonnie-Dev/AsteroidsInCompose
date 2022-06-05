package com.uxstate.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uxstate.domain.model.NearEarthObject
import kotlinx.coroutines.flow.Flow

@Dao
interface NeowsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNeows(neows:List<NeowsEntity>)

    @Query("DELETE from neowsentity")
    suspend fun clearNeows()

    @Query("SELECT * FROM neowsentity ORDER BY closeApproachDate ASC")
    suspend fun getAllNeows(startDate:String, endDate:String):Flow<List<NearEarthObject>>

    @Query("SELECT * FROM neowsentity WHERE closeApproachDate BETWEEN :startDate AND :endDate ORDER BY closeApproachDate ASC")
    suspend fun getWeeklyNeows(startDate: String, endDate: String): Flow<List<NearEarthObject>>

    @Query("SELECT * FROM neowsentity WHERE closeApproachDate BETWEEN :startDate AND :endDate ORDER By closeApproachDate ASC")
    suspend fun getTodayNeows(startDate: String,endDate: String):Flow<List<NearEarthObject>>



}