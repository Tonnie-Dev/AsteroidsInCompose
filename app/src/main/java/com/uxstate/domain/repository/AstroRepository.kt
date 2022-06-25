package com.uxstate.domain.repository

import com.uxstate.domain.model.AstroPhoto
import com.uxstate.util.Resource
import kotlinx.coroutines.flow.Flow


interface AstroRepository {


    suspend fun getFavoriteAstroPhoto(id: String): AstroPhoto?
    suspend fun insertAstroPhoto(photo: AstroPhoto)
    suspend fun deleteAstroPhoto(photo: AstroPhoto)
    suspend fun checkIfPhotoExistsInDatabase(photo: AstroPhoto):Boolean

    //ASTROPHOTOENTITY
fun getAstroPhotos(): Flow<Resource<List<AstroPhoto>>>



    //FAVPHOTOENTITY

    fun getFavPhotos(): Flow<List<AstroPhoto>?>
}