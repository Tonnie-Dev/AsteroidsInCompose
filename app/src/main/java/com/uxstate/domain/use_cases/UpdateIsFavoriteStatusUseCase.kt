package com.uxstate.domain.use_cases

import com.uxstate.domain.repository.AstroRepository

class UpdateIsFavoriteStatusUseCase (private val repository: AstroRepository) {

    suspend operator fun invoke(id:String){

        repository.updateIsFavoriteStatus(id)
    }
}