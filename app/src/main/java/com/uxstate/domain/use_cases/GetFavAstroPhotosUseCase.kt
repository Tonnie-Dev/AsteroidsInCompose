package com.uxstate.domain.use_cases

import com.uxstate.domain.model.AstroPhoto
import com.uxstate.domain.repository.AstroRepository
import com.uxstate.util.PhotoDateFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.time.ZoneOffset


//always depend on abstraction
class GetFavAstroPhotosUseCase(private val repository: AstroRepository) {

    //this flow return type is not wrapped in Resource  as it
    //doesn't need to propagate Resource.Loading .Success or
    //.Error
    operator fun invoke(dateFilter: PhotoDateFilter): Flow<List<AstroPhoto>> {

        val startDate = dateFilter.startDate.atZone(ZoneOffset.UTC)?.toInstant()?.toEpochMilli()!!
        val endDate = dateFilter.endDate.atZone(ZoneOffset.UTC)?.toInstant()?.toEpochMilli()!!

        val timeRange = (startDate..endDate)


        val list = repository.getFavPhotos()
                .map {

                    photosList ->
                    photosList.filter {
                        Timber.i("the list is empty - ${ photosList.isEmpty() } size is: ${photosList.size}")
                        it.timeStamp in timeRange

                    }
                }


        return list
    }
}