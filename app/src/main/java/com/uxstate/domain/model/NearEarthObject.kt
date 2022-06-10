package com.uxstate.domain.model

import java.time.LocalDate
import java.time.LocalDateTime

data class NearEarthObject(val id: Long,
                           val codename: String,
                           val closeApproachDate: LocalDate,
                           val estimatedDiameter: Double,
                           val relativeVelocity: Double,
                           val distanceFromEarth: Double,
                           val isPotentiallyHazardous: Boolean)
