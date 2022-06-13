package com.uxstate.presentation.overview_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uxstate.R
import com.uxstate.presentation.ui.theme.AsteroidsInComposeTheme

@Composable
fun NeowsItem(
    name: String,
    approachDate: String,
    isHazardous: Boolean,
    onClickNeowsItem: () -> Unit,
    modifier: Modifier = Modifier
) {


    Row(modifier = modifier.padding(16.dp).clickable {
        onClickNeowsItem ()

    }, horizontalArrangement = Arrangement.SpaceBetween) {

        Column() {
            Text(text = name )
            Text(text = approachDate)
        }


        Icon(
                painter = painterResource(
                        id = if (isHazardous) R.drawable.ic_sharp_sick_24
                        else R.drawable.ic_status_normal
                ),
        contentDescription = null
        )
    }



}

@Preview(name = "NeowsItem")
@Composable
fun PreviewNeowsItem() {

    AsteroidsInComposeTheme {

        NeowsItem(
                name = "Messier 31",
                approachDate = "2022-07-09",
                isHazardous = true,
                onClickNeowsItem = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()
        )

    }

}