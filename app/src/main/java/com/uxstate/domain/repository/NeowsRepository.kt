package com.uxstate.domain.repository

import com.uxstate.domain.model.AstroPicture
import com.uxstate.domain.model.NearEarthObject
import com.uxstate.util.Resource
import kotlinx.coroutines.flow.Flow


interface NeowsRepository {

    fun getAllNeowsObjects(
        startDate: String,
        endDate:String,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<NearEarthObject>>>


   suspend fun getAstroPictures():Flow<Resource<List<AstroPicture>>>
}