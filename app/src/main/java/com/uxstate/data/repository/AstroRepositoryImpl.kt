package com.uxstate.data.repository

import com.uxstate.data.local.AstroDatabase
import com.uxstate.data.mapper.toAstroPhoto
import com.uxstate.data.mapper.toAstroPhotoEntity
import com.uxstate.data.mapper.toEntity
import com.uxstate.data.mapper.toModel
import com.uxstate.data.remote.AstroAPI
import com.uxstate.domain.model.AstroPhoto
import com.uxstate.domain.repository.AstroRepository
import com.uxstate.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

//force single instance of our repository impl for the entire app
@Singleton

//use @Inject so that Hilt knows how to create AstroRepositoryImpl object
class AstroRepositoryImpl @Inject constructor(

        //we always depend on abstraction
    private val api: AstroAPI,
    private val db: AstroDatabase,


    ) : AstroRepository {


    private val dao = db.dao

    override fun fetchAstroPhotos(fetchFromRemote: Boolean): Flow<Resource<List<AstroPhoto>>> =
        //return a flow builder to have access to emit
        flow {
            //emit loading right away
            emit(Resource.Loading(isLoading = true))


            //start db query through a variable
            val localAstroPhotos = dao.getSavedAstroPhotos()


            /*at this point we have successfully loaded the cache, if
           * the database is empty then local photos list will be an
           * empty list*/

            emit(Resource.Success(data = localAstroPhotos.map { it.toAstroPhoto() }))


            //check if db is empty
            val isDbEmpty = localAstroPhotos.isEmpty()

            //determine if should load from cache

            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            //if db is not empty & is not fetchFromRemote return local listing
            if (shouldJustLoadFromCache) {
                //stop loading
                emit(Resource.Loading(false))


                //return to flow
                return@flow
            }
            //if db is empty and is fetchFromRemote start the API call

            val remotePhotos = try {

                api.getAstroPictures()

            } catch (e: IOException) {

                e.printStackTrace()
                //emit Error 1
                emit(Resource.Error(message = "Could not load data, check your internet connection"))

                //return null
                null

            } catch (e: HttpException) {


                e.printStackTrace()
                //emit Error 2
                emit(Resource.Error(message = "Unexpected error occurred"))

                //return null
                null


            }




            //null check returned date is not null then insert the remote photos

            remotePhotos?.let {

                //clear the local cache
                dao.clearAstroPhotos()

                dao.insertAstroPhotos(it.map { dto -> dto.toAstroPhotoEntity() })
            }

            //retrieve the saved AstroPhotos
            //One Single Source of truth - we ensure data comes for db
            emit(Resource.Success(data = dao.getSavedAstroPhotos().map { it.toAstroPhoto() }))

            //stop loading

            emit(Resource.Loading(false ))

        }

    override suspend fun insertAstroPhotos(astroPhotos: List<AstroPhoto>) {
        TODO("Not yet implemented")
    }

    override suspend fun getFavPhotoById(id: String): AstroPhoto? {
        TODO("Not yet implemented")
    }

    override fun getFavPhotos(): Flow<List<AstroPhoto>?> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavPhoto(photo: AstroPhoto) {
        TODO("Not yet implemented")
    }

    override suspend fun isFavPhotoSaved(photo: AstroPhoto): Boolean {
        TODO("Not yet implemented")
    }

    //REMOTE
    suspend fun getAstroPhotos(): List<AstroPhoto> {

        return api.getAstroPictures()
                .map { it.toModel() }

    }

    //LOCAL
    fun getFavoriteAstroPhotos(): Flow<List<AstroPhoto>?> {


        return dao.getFavoriteAstroPhotos()
                .map {
                    it?.map { photo -> photo.toAstroPhoto() }
                }

    }


    //LOCAL
    suspend fun getFavoriteAstroPhoto(id: String): AstroPhoto? {
        return dao.getFavoritePhotoById(id)
                ?.toAstroPhoto()
    }


    //LOCAL
    suspend fun insertAstroPhoto(photo: AstroPhoto) {
        dao.insertFavoriteAstroPhoto(photo.toEntity())


    }


    //LOCAL
    suspend fun deleteAstroPhoto(photo: AstroPhoto) {
        dao.deleteFavoritePhoto(photo.toEntity())
    }


    //LOCAL
    suspend fun checkIfPhotoExistsInDatabase(photo: AstroPhoto): Boolean {
        return dao.isFavPhotoSavedCheck(photo.date)
    }

}