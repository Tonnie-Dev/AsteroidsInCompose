package com.uxstate.presentation.overview_screen

import com.uxstate.domain.model.AstroPhoto

sealed class OverviewEvent {


    object OnRefreshAstroPhoto : OverviewEvent()
    object OnRetry : OverviewEvent()
    data class  OnMarkFavorite (val photo: AstroPhoto, val isFavorite:Boolean): OverviewEvent()
    data class  OnRemoveFromFavorites (val photo: AstroPhoto, val isFavorite:Boolean): OverviewEvent()




}