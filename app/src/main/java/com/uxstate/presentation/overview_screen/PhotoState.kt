package com.uxstate.presentation.overview_screen

import com.uxstate.domain.model.AstroPhotoEntity

data class PhotoState(
    val astroPhotoEntities: List<AstroPhotoEntity> = emptyList(),
    val isPhotoLoading: Boolean = false,
    val errorMessage: String? = null,



    val isPhotoFavorite:Boolean = false
)
