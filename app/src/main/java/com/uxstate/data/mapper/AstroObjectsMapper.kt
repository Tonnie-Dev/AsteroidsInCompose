package com.uxstate.data.mapper

import com.uxstate.data.local.AstroPhotoEntity
import com.uxstate.data.local.FavPhotoEntity
import com.uxstate.data.remote.dto.AstroPhotoDTO
import com.uxstate.domain.model.AstroPhoto


//DTO to Model

fun AstroPhotoDTO.toModel(): AstroPhoto {

    return AstroPhoto(
            title = this.title,
            explanation = this.explanation,
            mediaType = this.mediaType,
            url = this.url,
            date = this.date,
            isFavorite = false
    )

}

//DTO to AstroPhotoEntity

fun AstroPhotoDTO.toAstroPhotoEntity():AstroPhotoEntity {

    return AstroPhotoEntity(
            id = this.date,
            title = this.title,
            mediaType = this.mediaType,
            explanation = this.explanation,
            url = this.url,
            isFavorite = false
    )
}

//Entity to Model

fun AstroPhotoEntity.toAstroPhoto(): AstroPhoto {
    return AstroPhoto(
            title = this.title,
            explanation = this.explanation,
            mediaType = "image",
            url = this.url,
            date = this.id,
            isFavorite = this.isFavorite
    )
}



//FavPhotoEntity to Model

fun FavPhotoEntity.toAstroPhoto():AstroPhoto{

    return AstroPhoto(
            date =this.id,
            title = this.title,
            explanation =this.explanation,
            mediaType = this.mediaType,
            url = this.url,
            isFavorite = this.isFavorite
    )
}

//AstroPhoto to FavPhotoEntity

fun AstroPhoto.toFavPhotoEntity(): FavPhotoEntity{

    return FavPhotoEntity(
            id = this.date,
            title = this.title,
            mediaType = this.mediaType,
            explanation = this.explanation,
            url = this.url,
            isFavorite = true
    )
}


