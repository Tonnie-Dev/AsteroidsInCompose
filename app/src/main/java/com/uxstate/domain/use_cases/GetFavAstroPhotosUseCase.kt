package com.uxstate.domain.use_cases

import com.uxstate.domain.model.AstroPhoto
import com.uxstate.domain.repository.AstroRepository
import com.uxstate.util.PhotoDateFilter
import kotlinx.coroutines.flow.Flow


//always depend on abstraction
class GetFavAstroPhotosUseCase(private val repository: AstroRepository) {

//this flow return type is not wrapped in Resource  as it
    //doesn't need to propagate Resource.Loading .Success or
    //.Error
    operator fun invoke(dateFilter: PhotoDateFilter): Flow<List<AstroPhoto>>{


        return repository.getFavPhotos(dateFilter)
    }
}