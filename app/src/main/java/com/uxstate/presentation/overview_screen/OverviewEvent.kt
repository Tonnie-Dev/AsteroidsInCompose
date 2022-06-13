package com.uxstate.presentation.overview_screen

sealed class OverviewEvent {

    object OnRefreshNeows : OverviewEvent()
    object OnClickTodayButton : OverviewEvent()
    object OnClickTomorrowButton : OverviewEvent()
    object OnClickNextSevenDaysButton : OverviewEvent()
    object OnRefreshAstroPhoto : OverviewEvent()
    object OnTapPhoto : OverviewEvent()



}