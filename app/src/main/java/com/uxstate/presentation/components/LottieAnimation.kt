package com.uxstate.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.uxstate.R

@Composable
fun LottieAnimationComposable(onClick: () -> Unit, modifier: Modifier = Modifier) {


    Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //spec - hold reference to animation object
        val spec = LottieCompositionSpec.RawRes(R.raw.delete_animation)
        //state - manipulate progress
        var isDelete by remember {
            mutableStateOf(false)
        }

        //progress

        val progress by animateFloatAsState(targetValue = if (isDelete) 1f else 0f)
        //composition - render animation

        val composition by rememberLottieComposition(spec = spec)

        //lottie animation composable

        LottieAnimation(
                composition = composition,
                progress = progress,
                Modifier.clickable { onClick()
                isDelete = !isDelete
                }
        )

    }
}