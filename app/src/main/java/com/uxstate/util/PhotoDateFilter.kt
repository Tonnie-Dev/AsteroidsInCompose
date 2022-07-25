package com.uxstate.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset

sealed class PhotoDateFilter(val startDate: LocalDateTime, val endDate: LocalDateTime) {

    //start at 12:00 AM, end at current time
    object TodayPhotos : PhotoDateFilter(
            startDate = LocalDateTime.of(LocalDate.now(), LocalTime.MIN),
            endDate = LocalDateTime.now()
    )

    //start 4 days prior but excluding today, end yesterday 11:59 PM
    object RecentPhotos : PhotoDateFilter(
            startDate = LocalDateTime.now()
                    .minusDays(4)
                    .minusHours(24),

            endDate = LocalDateTime.of(
                    LocalDate.now()
                            .minusDays(1), LocalTime.MAX
            )
    )


    //start at epoch, end now
    object AllPhotos : PhotoDateFilter(
            startDate = LocalDateTime.ofEpochSecond(
                    50000, 50000,
                    ZoneOffset.UTC
            ),
            endDate = LocalDateTime.now()
    )
}
