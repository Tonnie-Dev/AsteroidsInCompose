package com.uxstate.presentation.overview_screen

sealed class OverviewEvent {


    object OnRefreshAstroPhoto : OverviewEvent()
    object OnClickAstroPhoto : OverviewEvent()
    object OnMarkFavorite : OverviewEvent()



}