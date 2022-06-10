package com.uxstate.util

sealed class DateFilter{

    object TodayDate: DateFilter()
    object NextSevenDays: DateFilter()
    object TomorrowDate: DateFilter()
}
