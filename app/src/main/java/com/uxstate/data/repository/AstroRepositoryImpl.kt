package com.uxstate.data.repository

import com.uxstate.data.json.JsonParser
import com.uxstate.data.local.AstroDatabase
import com.uxstate.data.mapper.toEntity
import com.uxstate.data.mapper.toModel
import com.uxstate.data.mapper.toPictureModel
import com.uxstate.data.remote.AstroPictureAPI
import com.uxstate.domain.model.AstroPhotoEntity
import com.uxstate.domain.model.NearEarthObject
import com.uxstate.domain.repository.AstroRepository
import com.uxstate.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton

//use @Inject so that Hilt knows how to create AstroRepositoryImpl object
class AstroRepositoryImpl @Inject constructor(

        //we always depend on abstraction
    private val api: AstroPictureAPI,
    private val db: AstroDatabase,


) : AstroRepository {

  
    override suspend fun getAstroPhotos(): List<AstroPhotoEntity>{

        return api.getAstroPictures().map { it.toPictureModel() }

    }


}