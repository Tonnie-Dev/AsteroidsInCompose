package com.uxstate.presentation.overview

import com.uxstate.domain.model.AstroPicture
import com.uxstate.domain.model.NearEarthObject

data class OverviewState(
    val neows: List<NearEarthObject> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val errorMessage: String? = null,
    val astroPictures: List<AstroPicture> = emptyList(),
    val isPictureLoading: Boolean = false,
    val pictureErrorMessage:String? = null,
val isPhotoTapped:Boolean = false
)
