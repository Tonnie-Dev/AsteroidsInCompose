package com.uxstate.presentation.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.uxstate.R
import com.uxstate.domain.model.AstroPhoto
import com.uxstate.util.LocalSpacing


@Composable
fun AstroPhotoComposable(
    modifier: Modifier = Modifier,
    photo: AstroPhoto,
    onTapPhoto: () -> Unit,
    onMarkAsFavorite: () -> Unit,
    onDeletePhoto: () -> Unit = {}
) {

    var isFavorite by remember { mutableStateOf(photo.isFavorite) }
    val spacing = LocalSpacing.current

    val painter =
        if (photo.url.startsWith("android.resource://")) {
            // Use painterResource for local drawables
            painterResource(id = R.drawable.thor)
        } else {

            // Use AsyncImagePainter(Coil) to load from internet
            rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                            .data(photo.url)
                            .placeholder(R.drawable.loading_animation)
                            .build()
            )
        }


    Card(
            modifier = modifier,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            shape = MaterialTheme.shapes.large
    ) {

        Box {

            Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                            .clip(MaterialTheme.shapes.large)
                            .clickable { onTapPhoto() }
                            .fillMaxWidth()
                            .aspectRatio(3f / 2f))


            Surface(
                    color = Color(0x7F000000),
                    shape = RoundedCornerShape(spacing.spaceMedium),
                    modifier = modifier
                            .align(Alignment.BottomCenter)
                            .padding(spacing.spaceMedium)
            ) {

                Text(
                        text = photo.title,
                        color = Color.White,
                        modifier = Modifier.padding(spacing.spaceSmall)
                )
            }
        }

        Column(modifier = Modifier.padding(spacing.spaceMedium)) {
            Text(
                    text = photo.explanation,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
            )
        }

        Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacing.spaceSmall)
        ) {

            if (isFavorite) {

                ButtonContainer(
                        modifier = Modifier.fillMaxWidth(.7f),
                        text = stringResource(id = R.string.delete_photo),
                        lottie = R.raw.delete_red_icon,
                        onClick = {
                            onDeletePhoto()
                            isFavorite = !isFavorite
                        }
                )


            } else {


                ButtonContainer(
                        modifier = Modifier.fillMaxWidth(.7f),
                        text = stringResource(id = R.string.favourite_label),
                        lottie = R.raw.green_heart_like,
                        onClick = {
                            onMarkAsFavorite()
                            isFavorite = !isFavorite
                        }
                )

            }


        }

    }

    @Composable
    fun ShareAstroPhoto(uri: String) {

        val context = LocalContext.current

        val intent = Intent().apply {

            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, uri)
            type = "image/jpg"
        }

        context.startActivity(Intent.createChooser(intent, null))
    }
}