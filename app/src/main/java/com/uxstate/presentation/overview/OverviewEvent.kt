package com.uxstate.presentation.overview

sealed class OverviewEvent {

    object Refreshing : OverviewEvent()
    object OnClickTodayButton : OverviewEvent()
    object OnClickWeeklyButton : OverviewEvent()
    object OnClickMonthlyButton : OverviewEvent()
}