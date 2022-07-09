package com.uxstate.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun SelectableBottomItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    text: String,
    onClick: () -> Unit,
    icon: ImageVector
) {

    Column(modifier  =modifier.clickable { onClick() }) {

        Icon(
                imageVector = icon,
                contentDescription = text,
                tint = if (isSelected) Color.Green else Color.Gray
        )
        Text(text = text)

    }

}