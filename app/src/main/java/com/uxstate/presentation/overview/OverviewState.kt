package com.uxstate.presentation.overview

import com.uxstate.domain.model.NearEarthObject

data class OverviewState(
    val neows: List<NearEarthObject> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
val errorMessage:String? = null
)
