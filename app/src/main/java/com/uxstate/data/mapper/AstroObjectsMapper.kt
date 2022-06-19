package com.uxstate.data.mapper

import com.uxstate.data.local.AstroPhotoEntity
import com.uxstate.data.remote.dto.AstroPhotoDTO
import com.uxstate.domain.model.AstroPhoto


//DTO to Model

fun AstroPhotoDTO.toPictureModel(): AstroPhoto {

    return AstroPhoto(
            title = this.title,
            explanation = this.explanation,
            mediaType = this.mediaType,
            url = this.url
    )

}

//Entity to Model

fun AstroPhotoEntity.toAstroPhoto(): AstroPhoto {

    return AstroPhoto(
            title = this.title,
            explanation = this.explanation,
            mediaType = "image",
            url = this.url
    )
}


