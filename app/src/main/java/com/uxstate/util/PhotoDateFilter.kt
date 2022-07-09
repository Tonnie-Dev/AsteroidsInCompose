package com.uxstate.util

import java.time.LocalDateTime
import java.time.ZoneOffset

sealed class PhotoDateFilter(val startDate: LocalDateTime) {

    object TodayPhotos : PhotoDateFilter(
            startDate = LocalDateTime.now()
                    .minusHours(24)
    )

    object RecentPhotos : PhotoDateFilter(
            startDate = LocalDateTime.now()
                    .minusDays(7)
    )

    object AllPhotos : PhotoDateFilter(
            startDate = LocalDateTime.ofEpochSecond(
                    50000, 50000,
                    ZoneOffset.UTC
            )
    )
}
