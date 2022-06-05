package com.uxstate.data.mapper

import com.uxstate.data.remote.dto.NearEarthObjectDTO
import com.uxstate.domain.model.NearEarthObject
import java.time.format.DateTimeFormatter
import java.util.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.ofPattern

import java.util.*
//DTO to Model

fun NearEarthObjectDTO.toNearEarthObject ():NearEarthObject {


    val pattern = "yyyy-MM-dd HH:mm"
    val dateFormatter = ofPattern(pattern, Locale.getDefault())
    val date =LocalDateTime.parse(this.closeApproachDate,dateFormatter)

    return NearEarthObject(
            id = this.id,
            codename = this.codename,
            closeApproachDate = date,
            estimatedDiameter = this.estimatedDiameter,
            relativeVelocity = this.relativeVelocity,
            distanceFromEarth = this.distanceFromEarth,
            isPotentiallyHazardous = this.isPotentiallyHazardous
    )
}

//Model to DTO

//Model to Entity

//Entity to Model