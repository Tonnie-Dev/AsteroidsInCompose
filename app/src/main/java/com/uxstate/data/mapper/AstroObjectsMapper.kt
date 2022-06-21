package com.uxstate.data.mapper

import com.uxstate.data.local.AstroPhotoEntity
import com.uxstate.data.remote.dto.AstroPhotoDTO
import com.uxstate.domain.model.AstroPhoto


//DTO to Model

fun AstroPhotoDTO.toModel(): AstroPhoto {

    return AstroPhoto(
            title = this.title,
            explanation = this.explanation,
            mediaType = this.mediaType,
            url = this.url,
            date = this.date
    )

}

//Entity to Model

fun AstroPhotoEntity.toAstroPhoto(): AstroPhoto {

    return AstroPhoto(
            title = this.title,
            explanation = this.explanation,
            mediaType = "image",
            url = this.url,
            date = this.id
    )
}

//Model to entity

fun AstroPhoto.toEntity(): AstroPhotoEntity {

    return AstroPhotoEntity(
            id = this.date,
            title = this.title,
            explanation = this.explanation,
            url = this.url,
            mediaType = this.mediaType,
            isFavorite = true
    )
}


