package com.uxstate.domain.use_cases

import com.uxstate.domain.model.NearEarthObject
import com.uxstate.domain.repository.AstroRepository
import com.uxstate.util.DateFilter
import com.uxstate.util.Resource
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class GetNeowsUseCase @Inject constructor(private val repository: AstroRepository) {

     operator fun invoke(
         startDate: LocalDateTime,
         endDate: DateFilter,
         fetchFromRemote: Boolean
    ): Flow<Resource<List<NearEarthObject>>> {



        val start = localDateToStringDate(startDate)
        val end = when (endDate) {

            is DateFilter.TodayDate -> localDateToStringDate(LocalDateTime.now())


            is DateFilter.TomorrowDate -> localDateToStringDate(
                    LocalDateTime.now()
                            .plusDays(1)
            )
            is DateFilter.NextSevenDays -> localDateToStringDate(
                    LocalDateTime.now()
                            .plusDays(7)
            )

        }


return repository.getAllNeowsObjects(
                startDate = start,
                endDate = end,
                fetchFromRemote = fetchFromRemote
        )



    }

    private fun localDateToStringDate(dateTime: LocalDateTime): String {

        val pattern = "yyyy-MM-dd"
        val dateFormatter = DateTimeFormatter.ofPattern(pattern)

        return dateTime.format(dateFormatter)

    }
}