package com.uxstate.data.repository

import com.uxstate.data.json.JsonParser
import com.uxstate.data.local.NeowsDatabase
import com.uxstate.data.remote.NeowsAPI
import com.uxstate.domain.model.NearEarthObject
import com.uxstate.domain.repository.NeowsRepository
import com.uxstate.util.Resource
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton

class NeowsRepositoryImpl @Inject constructor(

    //we always depend on abstraction
private val api:NeowsAPI,
private val db:NeowsDatabase,
private val jsonParser: JsonParser<NearEarthObject>

) :NeowsRepository{

    
    override suspend fun getAllNeowsObjects(
        startDate: LocalDate,
        endDate: LocalDate,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<NearEarthObject>>> {

        //emit loading status


        TODO("Not yet implemented")
    }

    override suspend fun getWeeklyNeowsObjects(
        startDate: LocalDate,
        endDate: LocalDate,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<NearEarthObject>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTodayNeowsObjects(
        startDate: LocalDate,
        endDate: LocalDate,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<NearEarthObject>>> {
        TODO("Not yet implemented")
    }

}