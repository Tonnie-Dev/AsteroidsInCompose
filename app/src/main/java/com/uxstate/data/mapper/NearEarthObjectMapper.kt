package com.uxstate.data.mapper

import com.uxstate.data.local.NeowsEntity
import com.uxstate.data.remote.dto.AstroPhotoDTO
import com.uxstate.domain.model.AstroPicture
import com.uxstate.domain.model.NearEarthObject
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

//DTO to Model

fun NearEarthObjectDTO.toNearEarthObject(): NearEarthObject {


    val pattern = "yyyy-MM-dd"
    val dateFormatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val date = LocalDate.parse(this.closeApproachDate, dateFormatter)

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
fun NearEarthObjectDTO.toEntity(): NeowsEntity {

    return NeowsEntity(
            id = this.id,
            codename = this.codename,
            closeApproachDate = this.closeApproachDate,
            estimatedDiameter = this.estimatedDiameter,
            relativeVelocity = this.relativeVelocity,
            distanceFromEarth = this.distanceFromEarth,
            isPotentiallyHazardous = this.isPotentiallyHazardous
    )
}

//Entity to Model

fun NeowsEntity.toModel(): NearEarthObject {

    val pattern = "yyyy-MM-dd"
    val dateFormatter = DateTimeFormatter.ofPattern(pattern)
    val date = LocalDate.parse(this.closeApproachDate, dateFormatter)

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


//Picture DTO to Picture Model

fun AstroPhotoDTO.toPictureModel(): AstroPicture {

    return AstroPicture(
            title = this.title,
            explanation = this.explanation,
            mediaType = this.mediaType,
            url = this.url
    )

}


