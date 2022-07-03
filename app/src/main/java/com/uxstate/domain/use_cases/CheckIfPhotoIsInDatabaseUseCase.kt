package com.uxstate.domain.use_cases

import com.uxstate.domain.model.AstroPhoto
import com.uxstate.domain.repository.AstroRepository

class CheckIfPhotoIsInDatabaseUseCase(private val repository: AstroRepository) {

    suspend operator fun invoke(photo: AstroPhoto):Boolean{

        return repository.isFavPhotoSaved(photo.id)
    }
}