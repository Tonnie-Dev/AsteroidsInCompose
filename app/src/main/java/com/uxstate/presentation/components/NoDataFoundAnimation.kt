package com.uxstate.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.uxstate.R


@Composable
fun NoDataFoundAnimation() {

    Column(modifier = Modifier.fillMaxSize()) {
        LottieAnimationPlaceHolder(
                lottie = R.raw.no_data_found,
                modifier = Modifier.weight(8f)
        )
        Text(
                text = stringResource(id = R.string.no_fav_photos),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.weight(2f)
        )
    }

}