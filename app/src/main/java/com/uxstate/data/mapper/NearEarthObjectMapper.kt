package com.uxstate.data.mapper

import com.uxstate.data.local.NeowsEntity
import com.uxstate.data.remote.dto.NearEarthObjectDTO
import com.uxstate.domain.model.NearEarthObject
import java.sql.Date
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

    val pattern = "yyyy-MM-dd HH:mm:ss"
    val dateFormatter = DateTimeFormatter.ofPattern(pattern)
    val date = LocalDateTime.parse(this.closeApproachDate,dateFormatter)

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

//Model to Entity




//Model to DTO



