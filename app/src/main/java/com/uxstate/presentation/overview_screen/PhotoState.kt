package com.uxstate.presentation.overview_screen

import com.uxstate.domain.model.AstroPhotoEntity

data class PhotoState(
    val neows: List<NearEarthObject> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val errorMessage: String? = null,
    val astroPhotoEntities: List<AstroPhotoEntity> = emptyList(),
    val isPictureLoading: Boolean = false,
    val pictureErrorMessage:String? = null,
    val isPhotoTapped:Boolean = false
)
