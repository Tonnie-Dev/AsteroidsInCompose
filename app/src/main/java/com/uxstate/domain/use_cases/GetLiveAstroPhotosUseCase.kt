package com.uxstate.domain.use_cases

import com.uxstate.domain.model.AstroPhoto
import com.uxstate.domain.repository.AstroRepository
import kotlinx.coroutines.flow.Flow


class GetLiveAstroPhotosUseCase(private val repository: AstroRepository) {


    operator fun invoke(): Flow<List<AstroPhoto>> {

        return repository.getLiveAstroPhotos()
    }
}