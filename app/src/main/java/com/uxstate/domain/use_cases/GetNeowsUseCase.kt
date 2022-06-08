package com.uxstate.domain.use_cases

import com.uxstate.domain.model.NearEarthObject
import com.uxstate.domain.repository.NeowsRepository
import com.uxstate.util.DateChanger
import com.uxstate.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class GetNeowsUseCase @Inject constructor(private val repository: NeowsRepository) {

    operator fun invoke(
        startDate: LocalDateTime = LocalDateTime.now(),
        endDate: DateChanger,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<NearEarthObject>>> = flow {


        val start = localDateToStringDate(startDate)
        val end = when (endDate) {

            is DateChanger.LastSevenDays -> localDateToStringDate(
                    LocalDateTime.now()
                            .minusDays(7)
            )
            is DateChanger.LastThirtyDays -> localDateToStringDate(
                    LocalDateTime.now()
                            .minusDays(30)
            )
            is DateChanger.TodayDate -> localDateToStringDate(LocalDateTime.now())

        }
        repository.getAllNeowsObjects(
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