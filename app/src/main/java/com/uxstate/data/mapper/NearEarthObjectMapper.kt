package com.uxstate.data.mapper

import com.uxstate.data.remote.dto.AstroPhotoDTO
import com.uxstate.domain.model.AstroPicture


//Picture DTO to Picture Model

fun AstroPhotoDTO.toPictureModel(): AstroPicture {

    return AstroPicture(
            title = this.title,
            explanation = this.explanation,
            mediaType = this.mediaType,
            url = this.url
    )

}


