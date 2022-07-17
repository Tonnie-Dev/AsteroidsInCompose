package com.uxstate.presentation.about_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
        Spacer(modifier = Modifier.height(150.dp))
        Text(
                text = stringResource(id = R.string.welcome_message),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        LottieAnimationPlaceHolder(
                lottie = R.raw.galaxy_anim,
                modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
                text = stringResource(id = R.string.welcome_header),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

