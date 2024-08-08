package com.uxstate.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.uxstate.presentation.ui.theme.AsteroidsInComposeTheme
import com.uxstate.util.LocalSpacing


@Composable
fun SelectableBottomItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    text: String,
    onClick: () -> Unit,
    icon: ImageVector
) {

    Column(
            modifier = modifier.clickable { onClick() },
            horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
                imageVector = icon,
                contentDescription = text,
                tint = if (isSelected) MaterialTheme.colorScheme.surfaceTint else Color.Gray,
                modifier = Modifier
                        .align(CenterHorizontally)
                        .size(40.dp)
        )
        Text(
                text = text,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.align(CenterHorizontally)
        )

    }

}


@Preview(showBackground = true)
@PreviewLightDark
@Composable
private fun SelectableBottomItemPreview() {

    val spacing = LocalSpacing.current
    AsteroidsInComposeTheme {

        Surface {

            SelectableBottomItem(
                    modifier = Modifier.padding(spacing.spaceSmall),
                    isSelected = false,
                    text = "Press Me",
                    onClick = {},
                    icon = Icons.Filled.HeartBroken
            )
        }
    }
}




