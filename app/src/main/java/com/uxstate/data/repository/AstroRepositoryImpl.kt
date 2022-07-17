package com.uxstate.data.repository

import com.uxstate.data.local.AstroDatabase
import com.uxstate.data.mapper.toAstroPhoto
import com.uxstate.data.mapper.toAstroPhotoEntity
import com.uxstate.data.mapper.toFavPhotoEntity
import com.uxstate.data.remote.AstroAPI
import com.uxstate.domain.model.AstroPhoto
import com.uxstate.domain.repository.AstroRepository
import com.uxstate.util.PhotoDateFilter
import com.uxstate.util.Resource
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject
import javax.inject.Singleton

//force single instance of our repository impl for the entire app
@Singleton

//use @Inject so that Hilt knows how to create AstroRepositoryImpl object
class AstroRepositoryImpl @Inject constructor(

        //we always depend on abstraction
    private val api: AstroAPI,
    db: AstroDatabase,


    ) : AstroRepository {


    private val dao = db.dao

    override fun fetchAstroPhotos(fetchFromRemote: Boolean): Flow<Resource<List<AstroPhoto>>> =
        //return a flow builder to have access to emit
        flow {
            //emit loading right away
            emit(Resource.Loading(isLoading = true))


            //start db query through a variable
            val localAstroPhotos = dao.fetchAstroPhotos()


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
            emit(
                    Resource.Success(
                            data = dao.fetchAstroPhotos()
                                    .map { it.toAstroPhoto() })
            )

            //stop loading

            emit(Resource.Loading(false))

        }

    override fun getLiveAstroPhotos(): Flow<List<AstroPhoto>> {

       return dao.getLiveAstroPhotos().map {

           it.map { astroPhotoEntity -> astroPhotoEntity.toAstroPhoto() }

       }
    }

    override suspend fun updateIsFavoriteStatus(photo: AstroPhoto, isFavorite: Boolean) =
        dao.updateIsFavoriteStatus(photo.id, isFavorite)


    override suspend fun getFavPhotoById(id: String): AstroPhoto? {

        return dao.getFavPhotoById(id)
                ?.toAstroPhoto()
    }

    override fun getFavPhotos(dateFilter: PhotoDateFilter): Flow<List<AstroPhoto>> {

        val startDate = dateFilter.startDate.atZone(ZoneId.systemDefault())
                .toInstant().toEpochMilli()
        return dao.getFavPhotos(startDate = startDate, endDate = System.currentTimeMillis())
                .map {

                    list ->
                    list.map { it.toAstroPhoto() }
                }
    }

    override suspend fun deleteFavPhoto(photo: AstroPhoto) {

        dao.deleteFavPhoto(photo.toFavPhotoEntity())
    }

    override suspend fun isFavPhotoSaved(id: String): Boolean {
        return dao.isFavPhotoSavedCheck(id)
    }

    override suspend fun insertFavPhoto(photo: AstroPhoto) {
        dao.insertFavoritePhoto(photo.toFavPhotoEntity())
    }



}