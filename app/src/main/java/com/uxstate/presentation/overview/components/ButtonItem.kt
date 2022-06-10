package com.uxstate.presentation.overview.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ButtonItem(onClickButton: () -> Unit, modifier: Modifier = Modifier) {


    Row(
            modifier = modifier
                    .clip(RoundedCornerShape(20.dp))
                    .clickable { onClickButton() }
                    .border(width = 1.dp, color = Color.Green, shape = RoundedCornerShape(20.dp))
                    .padding(8.dp)
    ) {

    }
    /*Button(onClick = { *//*TODO*//* }) {

    }*/
}