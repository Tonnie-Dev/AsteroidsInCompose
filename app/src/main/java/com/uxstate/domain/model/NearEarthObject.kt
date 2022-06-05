package com.uxstate.domain.model

import java.time.LocalDateTime

data class NearEarthObject(val id: Long,
                           val codename: String,
                           val closeApproachDate: LocalDateTime,
                           val estimatedDiameter: Double,
                           val relativeVelocity: Double,
                           val distanceFromEarth: Double,
                           val isPotentiallyHazardous: Boolean)
