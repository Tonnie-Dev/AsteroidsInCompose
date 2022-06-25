package com.uxstate.domain.use_cases

import com.uxstate.domain.model.AstroPhoto
import com.uxstate.domain.repository.AstroRepository
import com.uxstate.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class GetAstroPhotosUseCase(private val repository: AstroRepository) {

    operator  fun invoke(fetchFromRemote:Boolean): Flow<Resource<List<AstroPhoto>>> {

        return repository.fetchAstroPhotos(fetchFromRemote)
    }

}