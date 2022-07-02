package com.uxstate.presentation.overview_screen

import com.uxstate.domain.model.AstroPhoto

data class PhotoState(
    val astroPhotos: List<AstroPhoto> = emptyList(),
    val isPhotosListLoading: Boolean = false,
    val errorMessage: String? = null,

    )
