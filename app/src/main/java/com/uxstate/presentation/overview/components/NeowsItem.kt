package com.uxstate.presentation.overview.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.R

@Composable
fun NeowsItem(
    name: String,
    approachDate: String,
    isHazardous: Boolean,
    onClickNeowsItem: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier.clickable { onClickNeowsItem() }) {

        Text(text = name, modifier = Modifier.align(Alignment.TopStart))
        Text(text = approachDate, modifier = Modifier.align(Alignment.BottomStart))
        Icon(
                painter = painterResource(
                        id = if (isHazardous) R.drawable.ic_status_potentially_hazardous
                        else R.drawable.ic_status_normal
                ),
                contentDescription = null
        )
    }

}

@Preview(name = "NeowsItem")
@Composable
fun PreviewNeowsItem() {
    NeowsItem(
            name = "Messier 31",
            approachDate = "2022-07-09",
            isHazardous = true,
            onClickNeowsItem = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
    )
}