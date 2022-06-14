package com.uxstate.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

import java.time.LocalDate
import java.time.LocalDateTime
@Parcelize
data class NearEarthObject(val id: Long,
                           val codename: String,
                           val closeApproachDate: LocalDate,
                           val estimatedDiameter: Double,
                           val relativeVelocity: Double,
                           val distanceFromEarth: Double,
                           val isPotentiallyHazardous: Boolean):Parcelable
