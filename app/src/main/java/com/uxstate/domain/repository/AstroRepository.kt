package com.uxstate.domain.repository

import com.uxstate.domain.model.AstroPhoto
import kotlinx.coroutines.flow.Flow


interface AstroRepository {


    suspend fun getAstroPhotos(): List<AstroPhoto>
    fun getFavoriteAstroPhotos(): Flow<List<AstroPhoto>?>
    suspend fun getFavoriteAstroPhoto(id: String): AstroPhoto?
    suspend fun insertAstroPhoto(photo: AstroPhoto)
    suspend fun deleteAstroPhoto(photo: AstroPhoto)
    suspend fun checkIfPhotoExistsInDatabase(photo: AstroPhoto)
}