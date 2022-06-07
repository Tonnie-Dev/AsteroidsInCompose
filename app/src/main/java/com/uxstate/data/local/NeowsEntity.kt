package com.uxstate.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class NeowsEntity(@PrimaryKey val id: Long,
                       val codename: String,
                       val closeApproachDate: String,
                       val estimatedDiameter: Double,
                       val relativeVelocity: Double,
                       val distanceFromEarth: Double,
                       val isPotentiallyHazardous: Boolean)
