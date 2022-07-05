package com.uxstate.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition




//LottieAnimation
@Composable
fun LottieAnimationComposable(
    modifier: Modifier, lottie: Int
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(lottie))
    val progress by animateLottieCompositionAsState(composition = composition)

    LottieAnimation(composition = composition,
            progress = progress,
            modifier = modifier.size(80.dp))
}


//Lottie Animation Placeholder

@Composable
fun LottieAnimationPlaceHolder(modifier: Modifier, lottie: Int) {

    LottieAnimationComposable(modifier = modifier, lottie = lottie)

}