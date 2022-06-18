package com.uxstate.presentation.photo_screen.components

import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.rememberImagePainter
import com.uxstate.R
import com.uxstate.domain.model.AstroPicture


@Composable
fun AstroShareComposable(picture: AstroPicture) {
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