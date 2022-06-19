package com.uxstate.data.mapper

import com.uxstate.data.remote.dto.AstroPhotoDTO
import com.uxstate.domain.model.AstroPhotoEntity


//Picture DTO to Picture Model

fun AstroPhotoDTO.toPictureModel(): AstroPhotoEntity {

    return AstroPhotoEntity(
            title = this.title,
            explanation = this.explanation,
            mediaType = this.mediaType,
            url = this.url
    )

}


