package com.uxstate.presentation.overview_screen

import com.uxstate.domain.model.AstroPhoto

data class PhotoState(
    val astroPhotoEntities: List<AstroPhoto> = emptyList(),
    val isPhotoLoading: Boolean = false,
    val errorMessage: String? = null,



    val isPhotoFavorite:Boolean = false
)
