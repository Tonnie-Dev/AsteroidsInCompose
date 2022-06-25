package com.uxstate.data.repository

import com.uxstate.data.local.AstroDatabase
import com.uxstate.data.mapper.toAstroPhoto
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


    val dao = db.dao

    override fun fetchAstroPhotos(fetchFromRemote: Boolean): Flow<Resource<List<AstroPhoto>>> =
        //return a flow builder to have access to emit
        flow {
            //emit loading right away
            emit(Resource.Loading(isLoading = true))


            //start db query through a variable
            val localAstroPhotos = dao.getSavedAstroPhotos()
            //check if db is empty
            val isDbEmpty = localAstroPhotos.isEmpty()
            //if db is not empty & is not fetchFromRemote return local listing
            if(!isDbEmpty &&!fetchFromRemote){

                //emit the saved local list
                emit(Resource.Success(data = localAstroPhotos.map { it.toAstroPhoto() }))

                //stop loading
                emit(Resource.Loading(false))
                //return to flow
                return@flow
            }
            //if db is empty and is fetchFromRemote start the API call


                val remotePhotos =  try {

                        }
                        catch (e: IOException){

                            //emit Error
                            emit(Resource.Error(message = e?.localizedMessage))

                        }catch (e:HttpException){}


            //clear the local cache
            //insert the remote photos


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