package com.uxstate.presentation.overview_screen

import com.uxstate.domain.model.AstroPhoto

sealed class OverviewEvent {


    object OnRefreshAstroPhoto : OverviewEvent()
    data class  OnMarkFavorite (val photo:AstroPhoto): OverviewEvent()



}