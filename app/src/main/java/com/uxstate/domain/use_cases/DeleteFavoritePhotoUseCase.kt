package com.uxstate.domain.use_cases

import com.uxstate.domain.model.AstroPhoto
import com.uxstate.domain.repository.AstroRepository


//always depend on abstraction
class DeleteFavoritePhotoUseCase(private val repository: AstroRepository) {

    suspend operator fun invoke(photo: AstroPhoto) {

        repository.deleteAstroPhoto(photo)
    }
}