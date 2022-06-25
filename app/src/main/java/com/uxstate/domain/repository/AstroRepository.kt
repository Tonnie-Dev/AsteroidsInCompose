package com.uxstate.domain.repository

import com.uxstate.domain.model.AstroPhoto
import com.uxstate.util.Resource
import kotlinx.coroutines.flow.Flow


interface AstroRepository {

    //ASTROPHOTOENTITY
    fun fetchAstroPhotos(fetchFromRemote: Boolean): Flow<Resource<List<AstroPhoto>>>



    //FAVPHOTOENTITY
    suspend fun getFavPhotoById(id: String): AstroPhoto?
    fun getFavPhotos(): Flow<List<AstroPhoto>?>
    suspend fun deleteFavPhoto(photo: AstroPhoto)
    suspend fun isFavPhotoSaved(photo: AstroPhoto): Boolean
    suspend fun insertFavPhoto (photo: AstroPhoto)
}