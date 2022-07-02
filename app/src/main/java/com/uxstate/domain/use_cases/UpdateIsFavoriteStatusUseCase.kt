package com.uxstate.domain.use_cases

import com.uxstate.domain.model.AstroPhoto
import com.uxstate.domain.repository.AstroRepository

class UpdateIsFavoriteStatusUseCase (private val repository: AstroRepository) {

    suspend operator fun invoke(photo: AstroPhoto, isFavorite:Boolean){

        repository.updateIsFavoriteStatus(photo, isFavorite)
    }
}