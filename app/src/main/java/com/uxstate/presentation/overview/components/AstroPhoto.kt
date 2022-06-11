package com.uxstate.presentation.overview.components

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.uxstate.R
import com.uxstate.domain.model.AstroPicture

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun AstroPhoto(picture: AstroPicture, modifier: Modifier = Modifier) {
    val image = AnimatedImageVector.animatedVectorResource(R.drawable.loading_animation)
    val atEnd by remember { mutableStateOf(true) }

    val imgUri = picture.url.toUri()
            .buildUpon()
            .scheme("https")
            .build()

    Box(modifier = modifier) {

      /*  AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                        .data(imgUri)
                        .crossfade(true)
                        .size(Size.ORIGINAL)
                        .build(),
                placeholder = rememberAnimatedVectorPainter(image, atEnd),
                contentDescription = picture.title,
                contentScale = ContentScale.FillWidth,
                modifier = modifier
                        .align(Alignment.Center)
                        .size(420.dp, 240.dp)
                        .padding(20.dp)
        )
*/
        Image(
                painter = rememberImagePainter(
                        data = imgUri ,
                        builder = { crossfade(true)
                            placeholder(R.drawable.loading_animation)


                        }
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                        .align(Alignment.Center)
                        .size(420.dp, 240.dp)
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