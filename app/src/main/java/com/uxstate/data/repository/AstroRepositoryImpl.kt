package com.uxstate.data.repository
import com.uxstate.data.local.AstroDatabase
import com.uxstate.data.mapper.toAstroPhoto
import com.uxstate.data.mapper.toEntity
import com.uxstate.data.mapper.toModel
import com.uxstate.data.remote.AstroAPI
import com.uxstate.domain.model.AstroPhoto
import com.uxstate.domain.repository.AstroRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    //REMOTE
    override suspend fun getAstroPhotos(): List<AstroPhoto>{

        return api.getAstroPictures().map { it.toModel() }

    }

    //LOCAL
    override  fun getFavoriteAstroPhotos(): Flow<List<AstroPhoto>?>{


        return dao.getFavoriteAstroPhotos().map {
            it?.map { photo -> photo.toAstroPhoto() }
        }

    }


    //LOCAL
    override suspend fun getFavoriteAstroPhoto(id: String): AstroPhoto?{
        return dao.getFavoritePhotoById(id)?.toAstroPhoto()
    }


    //LOCAL
    override suspend fun insertAstroPhoto(photo: AstroPhoto) {
        dao.insertFavoriteAstroPhoto(photo.toEntity())


    }


    //LOCAL
    override suspend fun deleteAstroPhoto(photo: AstroPhoto) {
        dao.deleteFavoritePhoto(photo.toEntity())
    }


    //LOCAL
    override suspend fun checkIfPhotoExistsInDatabase(photo: AstroPhoto):Boolean {
        return dao.isFavPhotoSavedCheck(photo.date)
    }


}