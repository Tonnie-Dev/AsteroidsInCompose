package com.uxstate.presentation.components

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.core.net.toUri
import coil.compose.rememberImagePainter
import com.uxstate.R
import com.uxstate.domain.model.AstroPhoto
import com.uxstate.util.LocalSpacing
import timber.log.Timber

@OptIn(ExperimentalAnimationGraphicsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AstroPhotoComposable(
    modifier: Modifier = Modifier,
    photo: AstroPhoto,
    onTapPhoto: () -> Unit,
    onMarkAsFavorite: () -> Unit,
    onDeletePhoto: () -> Unit = {}
) {


    val spacing = LocalSpacing.current

    val imgUri = photo.url.toUri()
            .buildUpon()
            .scheme("https")
            .build()


    // var isMarkedFavorite = remember {photo.isFavorite}

    var isFavorite by remember(photo.isFavorite) { mutableStateOf(photo.isFavorite) }

    Card(
            modifier = modifier,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            shape = MaterialTheme.shapes.large
    ) {


        Box() {

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
                            .clip(MaterialTheme.shapes.large)
                            .clickable { onTapPhoto() }
                            // .size(100.dp, 240.dp)
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

        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {


            if (isFavorite) {

                //display delete option Assist Chip

                AssistChip(
                        onClick = {
                            isFavorite = !isFavorite
                            onDeletePhoto()



                        },
                        colors = AssistChipDefaults.assistChipColors(
                                leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        leadingIcon = {
                            Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = stringResource(
                                            id = R.string.delete_photo
                                    )
                            )
                        }, label = {
                    Text(text = stringResource(id = R.string.delete_photo))
                }
                )


            } else {
                //display Favourite AssistChip
                AssistChip(
                        onClick = {
                            isFavorite = !isFavorite
                            onMarkAsFavorite()

                        },
                        colors = AssistChipDefaults.assistChipColors
                        (leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant),
                        leadingIcon = {
                            Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = stringResource(R.string.favourite_label)
                            )
                        }, label = { Text(text = stringResource(id = R.string.favourite_label)) }
                )
            }
        }


    }

}

