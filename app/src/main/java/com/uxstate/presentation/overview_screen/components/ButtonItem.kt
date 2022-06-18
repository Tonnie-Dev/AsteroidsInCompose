package com.uxstate.presentation.overview_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.uxstate.util.LocalSpacing

@Composable
fun ButtonItem(
    onClickButton: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,

) {
    val spacing = LocalSpacing.current
    var isSelected =remember { false }
    Surface(
            color = if (isSelected) Color.Red else MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(spacing.spaceMedium),
            border = BorderStroke(width = 1.dp, MaterialTheme.colorScheme.outline),
            modifier = modifier
                    .clip(RoundedCornerShape(spacing.spaceMedium))
                    .clickable {

                        onClickButton()
                        isSelected = !isSelected
                    }


    ) {

        Text(text = text, modifier = Modifier.padding(spacing.spaceSmall), textAlign = TextAlign.Center)

    }
}