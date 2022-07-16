package com.uxstate.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset

sealed class PhotoDateFilter(val startDate: LocalDateTime) {

    object TodayPhotos : PhotoDateFilter(
            startDate = LocalDateTime.of(LocalDate.now(), LocalTime.MIN)
    )

    object RecentPhotos : PhotoDateFilter(
            startDate = LocalDateTime.now()
                    .minusDays(4).minusHours(24)
    )

    object AllPhotos : PhotoDateFilter(
            startDate = LocalDateTime.ofEpochSecond(
                    50000, 50000,
                    ZoneOffset.UTC
            )
    )
}
