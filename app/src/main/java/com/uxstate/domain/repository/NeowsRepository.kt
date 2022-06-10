package com.uxstate.domain.repository

import com.uxstate.domain.model.NearEarthObject
import com.uxstate.util.Resource
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate


interface NeowsRepository {

    fun getAllNeowsObjects(
        startDate: String,
        endDate:String,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<NearEarthObject>>>


}