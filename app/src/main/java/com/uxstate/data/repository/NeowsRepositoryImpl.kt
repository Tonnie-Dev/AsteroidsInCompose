package com.uxstate.data.repository

import com.uxstate.data.json.JsonParser
import com.uxstate.data.local.NeowsDatabase
import com.uxstate.data.remote.NeowsAPI
import com.uxstate.domain.model.NearEarthObject
import com.uxstate.domain.repository.NeowsRepository
import com.uxstate.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton

class NeowsRepositoryImpl @Inject constructor(

        //we always depend on abstraction
    private val api: NeowsAPI,
    private val db: NeowsDatabase,
    private val jsonParser: JsonParser<NearEarthObject>

) : NeowsRepository {

val dao = db.dao
    override suspend fun getAllNeowsObjects(
        startDate: String,
        endDate: String,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<NearEarthObject>>> =

        //use a flow builder to get access to emit()
        flow {

            //loading
            emit(Resource.Loading(isLoading = true))

            //data db query
            val neowsList = dao.getAllNeows()



        }

    override suspend fun getWeeklyNeowsObjects(
        startDate: String,
        endDate: String,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<NearEarthObject>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTodayNeowsObjects(
        startDate: String,
        endDate: String,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<NearEarthObject>>> {
        TODO("Not yet implemented")
    }

}