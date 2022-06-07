package com.uxstate.data.mapper

import com.uxstate.data.local.NeowsEntity
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

//DTO to Entity
fun NearEarthObjectDTO.toEntity():NeowsEntity {

    return  NeowsEntity(

            codename = this.codename,
            closeApproachDate = this.closeApproachDate,
            estimatedDiameter = this.estimatedDiameter,
            relativeVelocity = this.relativeVelocity,
            distanceFromEarth = this.distanceFromEarth,
            isPotentiallyHazardous = this.isPotentiallyHazardous
    )
}

//Entity to Model

fun NeowsEntity.toModel():NearEarthObject{


    return NearEarthObject(
            id = this.id,
            codename = "",
            closeApproachDate =,
            estimatedDiameter = 0.0,
            relativeVelocity = 0.0,
            distanceFromEarth = 0.0,
            isPotentiallyHazardous = false
    )
}

//Model to Entity




//Model to DTO



