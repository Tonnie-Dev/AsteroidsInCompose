package com.uxstate.domain.repository

import com.uxstate.domain.model.AstroPhoto
import com.uxstate.util.Resource
import kotlinx.coroutines.flow.Flow


interface AstroRepository

{

    /*Put functions to access data or manage data - this operations should
    be the ones done from the UI not automatic (e.g. populating remote
    data into db should not be a function of repository definition)*/

    //ASTROPHOTOENTITY
    fun fetchAstroPhotos(fetchFromRemote: Boolean): Flow<Resource<List<AstroPhoto>>>



    //FAVPHOTOENTITY
    suspend fun getFavPhotoById(id: String): AstroPhoto?
    fun getFavPhotos(): Flow<List<AstroPhoto>?>
    suspend fun deleteFavPhoto(photo: AstroPhoto)
    suspend fun isFavPhotoSaved(photo: AstroPhoto): Boolean
}