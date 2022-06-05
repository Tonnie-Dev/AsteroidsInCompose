package com.uxstate.data.remote.dto

data class NearEarthObjectDTO(val id: Long,
                              val codename: String,
                              val closeApproachDate: String,
                              val absoluteMagnitude: Double,
                              val estimatedDiameter: Double,
                              val relativeVelocity: Double,
                              val distanceFromEarth: Double,
                              val isPotentiallyHazardous: Boolean)
