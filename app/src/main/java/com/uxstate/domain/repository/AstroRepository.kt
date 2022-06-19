package com.uxstate.domain.repository

import com.uxstate.domain.model.AstroPhotoEntity
import com.uxstate.domain.model.NearEarthObject
import com.uxstate.util.Resource
import kotlinx.coroutines.flow.Flow


interface AstroRepository {


   suspend fun getAstroPhotos():List<AstroPhotoEntity>
}