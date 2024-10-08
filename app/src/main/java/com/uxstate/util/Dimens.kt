package com.uxstate.util

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


//data class with the default values
data class Dimens(
    val spaceDefault: Dp = 0.dp,
    val spaceSingleDp:Dp = 1.dp,
    val spaceDoubleDp: Dp = 2.dp,
    val spaceExtraSmall: Dp = 4.dp,
    val spaceSmall: Dp = 8.dp,
    val spaceMedium: Dp = 16.dp,
    val spaceLarge: Dp = 32.dp,
    val spaceExtraLarge: Dp = 64.dp
)


val LocalSpacing = compositionLocalOf { Dimens() }






//value
data class User(val name: String, val age: Int)

//Create a CompositionLocal
val LocalActiveUser = compositionLocalOf<User> { error("No user found!") }









