package com.uxstate.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*


//LottieAnimation
@Composable
fun LottieAnimationComposable(
    modifier: Modifier, lottie: Int
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(lottie))
    val progress by animateLottieCompositionAsState(
            composition = composition,
            iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
            composition = composition,
            progress = progress,
            modifier = modifier.size(50.dp)
    )
}




@Composable
fun LottieAnimationPlaceHolder(modifier: Modifier = Modifier, lottie: Int) {

    LottieAnimationComposable(modifier = modifier, lottie = lottie)

}