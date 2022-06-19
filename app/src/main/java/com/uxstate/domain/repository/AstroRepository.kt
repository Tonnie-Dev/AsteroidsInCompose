package com.uxstate.domain.repository

import com.uxstate.domain.model.AstroPhotoEntity


interface AstroRepository {


   suspend fun getAstroPhotos():List<AstroPhotoEntity>
}