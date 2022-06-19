package com.uxstate.data.repository

import com.uxstate.data.json.JsonParser
import com.uxstate.data.local.NeowsDatabase
import com.uxstate.data.mapper.toEntity
import com.uxstate.data.mapper.toModel
import com.uxstate.data.mapper.toPictureModel
import com.uxstate.data.remote.AstroPictureAPI
import com.uxstate.domain.model.AstroPicture
import com.uxstate.domain.model.NearEarthObject
import com.uxstate.domain.repository.NeowsRepository
import com.uxstate.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton

//use @Inject so that Hilt knows how to create NeowsRepositoryImpl object
class NeowsRepositoryImpl @Inject constructor(

        //we always depend on abstraction
    private val api: AstroPictureAPI,
    private val db: NeowsDatabase,
    private val jsonParser: JsonParser<NearEarthObjectDTO>

) : NeowsRepository {

    val dao = db.dao
    override  fun getAllNeowsObjects(
        startDate: String,
        endDate: String,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<NearEarthObject>>> =

        //use a flow builder to get access to emit()
        flow {

            //loading
            emit(Resource.Loading(isLoading = true))

            //data db query
            val localNeowsList = dao.getAllNeows(startDate, endDate)

            /*at this point we have successfully loaded the cache, if
              the database is empty then neowsList  will be an
         empty list*/

            //we emit the local list
            emit(Resource.Success(data = localNeowsList.map { it.toModel() }))


            /* check if we actually need the API Call - if we don't
             swipe to refresh the we have an up to date listings and we
            don't need an API call*/

            val isDbEmpty = localNeowsList.isEmpty()

            val isLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (isLoadFromCache) {

                //change loading status to false
                emit(Resource.Loading(false))
                //return
                return@flow
            }

            //Else proceed to remote call

            val remoteNeows = try {

             val response =   api.getNearEarthObjects(startDate, endDate)

                jsonParser.parseJson(response)

            } //parsing issue, no internet connection
            catch (e: IOException) {

                e.printStackTrace()
                emit(Resource.Error(message = "Could not load data"))
                null
            }
            //invalid http response, incomplete response
            catch (e: HttpException) {
                e.printStackTrace()

                emit(Resource.Error(message = "Could not load data"))
                null
            }


            //INSERT remote neows into the db


            //null-check remote neows

            remoteNeows?.let { neows ->


                dao.clearNeows()
                dao.insertNeows(neows.map { it.toEntity()})


                val updatedLocalCacheNeows = dao.getAllNeows(startDate, endDate)
                //One Single Source of truth - we ensure data comes for db

                emit(Resource.Success(updatedLocalCacheNeows.map { it.toModel() }))
                emit(Resource.Loading(isLoading = false))
            }




        }

    override suspend fun getAstroPictures(): List<AstroPicture>{

        return api.getAstroPictures().map { it.toPictureModel() }

    }


}