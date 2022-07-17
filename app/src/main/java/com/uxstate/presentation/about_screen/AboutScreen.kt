package com.uxstate.presentation.about_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.uxstate.R
import com.uxstate.presentation.components.LottieAnimationPlaceHolder

@Composable
fun AboutScreen() {
    Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
Text(text = "Welcome")
        
        LottieAnimationPlaceHolder(lottie = R.raw.galaxy_anim)
    }
}