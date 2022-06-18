package com.uxstate.presentation.photo_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.rememberImagePainter
import com.uxstate.R
import com.uxstate.domain.model.AstroPicture



@ExperimentalMaterial3Api
@Composable
fun AstroShareComposable(picture: AstroPicture, modifier: Modifier = Modifier) {


    Card(
            modifier = modifier,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            shape = MaterialTheme.shapes.large
    ) {

    }
    val imgUri = picture.url.toUri()
            .buildUpon()
            .scheme("https")
            .build()
    Box() {
        Column(modifier = Modifier.align(Alignment.TopStart)) {


            Image(
                    painter = rememberImagePainter(
                            data = imgUri,
                            builder = {
                                crossfade(true)
                                placeholder(R.drawable.loading_animation)
                            }
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier

                            .size(420.dp, 240.dp)
                            .padding(8.dp)

            )

            //Title
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = picture.title, fontWeight = Bold)
            Spacer(modifier = Modifier.height(16.dp))
            //About
            Text(text = picture.explanation, softWrap = true, modifier = Modifier.width(400.dp))

        }

    }

}