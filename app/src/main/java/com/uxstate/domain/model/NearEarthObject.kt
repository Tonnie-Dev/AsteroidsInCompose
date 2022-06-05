package com.uxstate.domain.model

data class NearEarthObject(val id: Long,
                           val codename: String,
                           val closeApproachDate: String,
                           val absoluteMagnitude: Double,
                           val estimatedDiameter: Double,
                           val relativeVelocity: Double,
                           val distanceFromEarth: Double,
                           val isPotentiallyHazardous: Boolean)
