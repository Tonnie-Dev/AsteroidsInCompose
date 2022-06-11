package com.uxstate.presentation.overview.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.uxstate.domain.model.AstroPicture

@Composable
fun AstroPhoto(picture: AstroPicture, modifier: Modifier = Modifier) {

    val imgUrl = picture.url.toUri()
            .buildUpon()
            .scheme("https")
            .build()

    Box(modifier = modifier) {

        AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                        .data(imgUrl)
                        .crossfade(true)
                        .size(Size.ORIGINAL)
                        .build(),
                contentDescription = picture.title,
                contentScale = ContentScale.FillWidth,
                modifier = modifier
                        .align(Alignment.Center)
                        .size(300.dp, 200.dp)
                        .padding(20.dp)
        )


        Surface(
                color = Color.Black,
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .padding(8.dp)
        ) {

            Text(text = picture.title, color = Color.White, modifier = Modifier.padding(8.dp))
        }

    }


}


////https://coil-kt.github.io/coil/compose/