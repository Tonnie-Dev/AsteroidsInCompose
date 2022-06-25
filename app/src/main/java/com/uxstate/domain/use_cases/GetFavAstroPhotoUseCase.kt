package com.uxstate.domain.use_cases

import com.uxstate.domain.model.AstroPhoto
import com.uxstate.domain.repository.AstroRepository


//always depend on abstraction
class GetFavAstroPhotoUseCase (private val repository: AstroRepository){

    suspend operator fun invoke(id:String): AstroPhoto?{
       return repository.getFavPhotoById(id)

    }

}