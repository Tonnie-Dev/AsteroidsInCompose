package com.uxstate.presentation.overview.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ButtonItem(
    onClickButton: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,

) {

    var isSelected =remember { false }
    Surface(
            color = if (isSelected) Color.Red else Color.Green,
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(width = 1.dp, Color.Green),
            modifier = modifier
                    .clip(RoundedCornerShape(20.dp))
                    .clickable {

                        onClickButton()
                        isSelected = !isSelected
                    }


    ) {

        Text(text = text, modifier = Modifier.padding(8.dp), textAlign = TextAlign.Center)

    }
}