package com.uxstate.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.uxstate.R


@Composable
fun NoConnectionAnimation() {

    Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center
    ) {
        LottieAnimationPlaceHolder(
                lottie = R.raw.no_connection,
                modifier = Modifier.size(400.dp)
        )
        Text(
                text = stringResource(id = R.string.could_not_load_data),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(CenterHorizontally)
        )
    }

}