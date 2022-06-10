package com.uxstate.presentation.overview

sealed class OverviewEvent {

    object Refreshing : OverviewEvent()
    object OnClickTodayButton : OverviewEvent()
    object OnClickTomorrowButton : OverviewEvent()
    object OnClickNextSevenDaysButton : OverviewEvent()


}