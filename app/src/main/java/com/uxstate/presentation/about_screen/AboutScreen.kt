package com.uxstate.presentation.about_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.uxstate.R
import com.uxstate.presentation.components.LottieAnimationPlaceHolder

@Destination
@Composable
fun AboutScreen() {
    Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome")

        LottieAnimationPlaceHolder(
                lottie = R.raw.galaxy_anim,
                modifier = Modifier.size(300.dp)
        )

        Text(text = stringResource(id = R.string.welcome_header))
    }
}

