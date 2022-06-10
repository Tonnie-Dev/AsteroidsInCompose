package com.uxstate.domain.repository

import com.uxstate.data.remote.dto.PictureOfTheDayDTO
import com.uxstate.domain.model.AstroPicture
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


    fun getAstroPictures():Flow<Resource<List<AstroPicture>>>
}