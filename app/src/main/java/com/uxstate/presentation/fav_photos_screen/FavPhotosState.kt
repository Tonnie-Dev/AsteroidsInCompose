package com.uxstate.presentation.fav_photos_screen

import com.uxstate.domain.model.AstroPhoto

data class FavPhotosState(
    val favoritePhotosList: List<AstroPhoto> = emptyList()
)
