package com.uxstate.presentation.overview_screen

import com.uxstate.domain.model.AstroPhotoEntity
import com.uxstate.domain.model.NearEarthObject

data class OverviewState(
    val neows: List<NearEarthObject> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val errorMessage: String? = null,
    val astroPhotoEntities: List<AstroPhotoEntity> = emptyList(),
    val isPictureLoading: Boolean = false,
    val pictureErrorMessage:String? = null,
    val isPhotoTapped:Boolean = false
)
