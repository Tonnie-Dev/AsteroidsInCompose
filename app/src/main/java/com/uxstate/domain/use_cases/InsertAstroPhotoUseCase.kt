package com.uxstate.domain.use_cases

import com.uxstate.domain.repository.AstroRepository

class InsertAstroPhotoUseCase(private val repository: AstroRepository) {

    suspend operator fun invoke(id: String) {

        repository.deleteAstroPhoto(id)

    }
}