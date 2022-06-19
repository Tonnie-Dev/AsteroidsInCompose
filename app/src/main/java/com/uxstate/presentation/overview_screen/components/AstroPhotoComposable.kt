package com.uxstate.presentation.overview_screen.components

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.core.net.toUri
import coil.compose.rememberImagePainter
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import com.uxstate.R
import com.uxstate.domain.model.AstroPicture
import com.uxstate.util.LocalSpacing

@OptIn(ExperimentalAnimationGraphicsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AstroPhotoComposable(
    picture: AstroPicture,
    modifier: Modifier = Modifier,
    onTapPhoto: () -> Unit
) {

    val spacing = LocalSpacing.current

    val imgUri = picture.url.toUri()
            .buildUpon()
            .scheme("https")
            .build()



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
                        text = picture.title,
                        color = Color.White,
                        modifier = Modifier.padding(spacing.spaceSmall)
                )
            }
        }

        Column(modifier = Modifier.padding(spacing.spaceMedium)) {
            Text(
                    text = picture.explanation,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
            )
        }

        FlowRow(
                modifier = Modifier.fillMaxWidth(),
                mainAxisSpacing = spacing.spaceSmall,
                mainAxisSize = SizeMode.Wrap
        ) {

            //Favourite AssistChip
            AssistChip(
                    onClick = { /*TODO*/ },
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

