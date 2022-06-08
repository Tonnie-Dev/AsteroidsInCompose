package com.uxstate.util

sealed class DateChanger{

    object TodayDate: DateChanger()
    object LastSevenDays: DateChanger()
    object LastThirtyDays: DateChanger()
}
