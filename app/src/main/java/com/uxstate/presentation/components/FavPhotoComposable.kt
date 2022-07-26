package com.uxstate.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uxstate.R
import com.uxstate.domain.model.AstroPhoto
import com.uxstate.util.LocalSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavPhotoComposable(
    modifier: Modifier = Modifier,
    photo: AstroPhoto,
    onTapPhoto: () -> Unit,
    onDeletePhoto:  () -> Unit
) {


    val spacing = LocalSpacing.current

    val imgUri = photo.url.toUri()
            .buildUpon()
            .scheme("https")
            .build()

    val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                    .data(imgUri)
                    .placeholder(R.drawable.loading_animation)
                    .crossfade(true)
                    .build()
    )

    Card(
            modifier = modifier,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            shape = MaterialTheme.shapes.large
    ) {


        Box() {

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

        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
            AssistChip(
                    onClick = {
                        onDeletePhoto()

                    },
                    colors = AssistChipDefaults.assistChipColors(
                            leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    leadingIcon = {
                        LottieAnimationPlaceHolder(lottie = R.raw.delete_red_icon)


                    }, label = {
                Text(text = stringResource(id = R.string.delete_photo))
            }
            )


        }


    }

}

