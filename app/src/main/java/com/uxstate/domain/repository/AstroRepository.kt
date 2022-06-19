package com.uxstate.domain.repository

import com.uxstate.domain.model.AstroPhoto


interface AstroRepository {


   suspend fun getAstroPhotos():List<AstroPhoto>
   suspend fun getFavoriteAstroPhotos():List<AstroPhoto>
}