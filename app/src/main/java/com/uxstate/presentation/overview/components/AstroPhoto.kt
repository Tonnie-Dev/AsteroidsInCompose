package com.uxstate.presentation.overview.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.net.toUri
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.uxstate.R
import com.uxstate.domain.model.AstroPicture

@Composable
fun AstroPhoto(picture: AstroPicture) {

    val imgUrl = picture.url.toUri()
            .buildUpon()
            .scheme("https")
            .build()
    
    //https://coil-kt.github.io/coil/compose/
    AsyncImage(model = ImageRequest.Builder(LocalContext.current)
            .data(imgUrl)
            .crossfade(true).build(),

            placeholder = painterResource(R.drawable.loading_animation),
            contentDescription = picture.title,
            contentScale = ContentScale.Crop,
    )


}