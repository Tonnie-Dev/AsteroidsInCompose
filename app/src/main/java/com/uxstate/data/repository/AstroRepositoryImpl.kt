package com.uxstate.data.repository
import com.uxstate.data.local.AstroDatabase
import com.uxstate.data.mapper.toPictureModel
import com.uxstate.data.remote.AstroAPI
import com.uxstate.domain.model.AstroPhoto
import com.uxstate.domain.repository.AstroRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton

//use @Inject so that Hilt knows how to create AstroRepositoryImpl object
class AstroRepositoryImpl @Inject constructor(

        //we always depend on abstraction
    private val api: AstroAPI,
    private val db: AstroDatabase,


    ) : AstroRepository {


    override suspend fun getAstroPhotos(): List<AstroPhoto>{

        return api.getAstroPictures().map { it.toPictureModel() }

    }

    override suspend fun getFavoriteAstroPhotos(): List<AstroPhoto> {
        TODO("Not yet implemented")
    }


}