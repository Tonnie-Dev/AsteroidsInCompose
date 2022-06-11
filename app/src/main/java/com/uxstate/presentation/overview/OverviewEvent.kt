package com.uxstate.presentation.overview

sealed class OverviewEvent {

    object OnRefreshNeows : OverviewEvent()
    object OnClickTodayButton : OverviewEvent()
    object OnClickTomorrowButton : OverviewEvent()
    object OnClickNextSevenDaysButton : OverviewEvent()
    object OnRefreshAstroPhoto : OverviewEvent()


}