package com.uxstate.presentation.overview

sealed class OverviewEvents {

    object Refreshing : OverviewEvents()
    object OnClickTodayButton : OverviewEvents()
    object OnClickWeeklyButton : OverviewEvents()
    object OnClickMonthlyButton : OverviewEvents()
}