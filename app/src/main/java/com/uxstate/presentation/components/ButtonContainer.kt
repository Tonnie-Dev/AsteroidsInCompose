package com.uxstate.presentation.components

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.uxstate.R
import com.uxstate.presentation.ui.theme.AsteroidsInComposeTheme
import com.uxstate.util.LocalSpacing

@Composable
fun ButtonContainer(
    text: String,
    @RawRes lottie: Int,
    onClick: () -> Unit,
    modifier: Modifier
) {

    val spacing = LocalSpacing.current

    ElevatedButton(modifier = modifier, onClick = onClick) {
        
        Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                                horizontal = spacing.spaceMedium,
                                vertical = spacing.spaceDoubleDp
                        ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
        ) {

            LottieAnimationPlaceHolder(
                    lottie = lottie,
                    modifier = Modifier.size(spacing.spaceLarge)
            )
            Spacer(modifier = Modifier.width(spacing.spaceSmall))
            Text(text = text, style = MaterialTheme.typography.titleSmall)
        }

    }
}


@PreviewLightDark
@Composable
private fun AddButtonContainerPreview() {
    val spacing = LocalSpacing.current
    AsteroidsInComposeTheme {

        Surface {
            ButtonContainer(
                    modifier = Modifier.fillMaxWidth(.6f),
                    text = "Mark as Favorite",
                    lottie = R.raw.green_heart_like,
                    onClick = {}
            )
        }
    }
}


@PreviewLightDark
@Composable
private fun DeleteButtonContainerPreview() {
    val spacing = LocalSpacing.current
    AsteroidsInComposeTheme {

        Surface {
            ButtonContainer(modifier = Modifier.fillMaxWidth(.6f),
                    text = "Delete",
                    lottie = R.raw.delete_red_icon,
                    onClick = {}
            )
        }
    }
}