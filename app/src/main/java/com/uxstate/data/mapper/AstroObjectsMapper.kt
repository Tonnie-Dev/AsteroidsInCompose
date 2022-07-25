package com.uxstate.data.mapper

import com.uxstate.data.local.AstroPhotoEntity
import com.uxstate.data.local.FavPhotoEntity
import com.uxstate.data.remote.dto.AstroPhotoDTO
import com.uxstate.domain.model.AstroPhoto



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



//AstroPhotoEntity to Model
fun AstroPhotoEntity.toAstroPhoto(): AstroPhoto {
    return AstroPhoto(
            title = this.title,
            explanation = this.explanation,
            mediaType = "image",
            url = this.url,
            id = this.id,
            isFavorite = this.isFavorite,
            timeStamp = System.currentTimeMillis()
    )
}



//FavPhotoEntity to Model
fun FavPhotoEntity.toAstroPhoto():AstroPhoto{

    return AstroPhoto(
            id =this.id,
            title = this.title,
            explanation =this.explanation,
            mediaType = this.mediaType,
            url = this.url,
            isFavorite = this.isFavorite,
            timeStamp = this.timeStamp
    )
}

//AstroPhoto to FavPhotoEntity
fun AstroPhoto.toFavPhotoEntity(): FavPhotoEntity{

    return FavPhotoEntity(
            id = this.id,
            timeStamp = System.currentTimeMillis(),
            title = this.title,
            mediaType = this.mediaType,
            explanation = this.explanation,
            url = this.url,
            isFavorite = true
    )
}




