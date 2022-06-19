package com.uxstate.domain.use_cases

import com.uxstate.domain.model.AstroPhoto
import com.uxstate.domain.repository.AstroRepository
import kotlinx.coroutines.flow.Flow


//always depend on abstraction
class GetFavAstroPhotosUseCase(private val repository: AstroRepository) {


    operator fun invoke(): Flow<List<AstroPhoto>?>{


        return repository.getFavoriteAstroPhotos() 
    }
}