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
fun NoDataFoundAnimation() {

    Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center
    ) {
        LottieAnimationPlaceHolder(
                lottie = R.raw.empty_box,
                modifier = Modifier.size(400.dp)
        )
        Text(
                text = stringResource(id = R.string.no_fav_photos),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(CenterHorizontally)
        )
    }

}