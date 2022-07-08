package com.uxstate.presentation.components

import android.content.Intent
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.core.net.toUri
import coil.compose.rememberImagePainter
import com.uxstate.R
import com.uxstate.domain.model.AstroPhoto
import com.uxstate.util.LocalSpacing

@OptIn(ExperimentalAnimationGraphicsApi::class, ExperimentalMaterial3Api::class)
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

    val imgUri = photo.url.toUri()
            .buildUpon()
            .scheme("https")
            .build()





    val intent: Intent = Intent().apply {

        action = Intent.ACTION_SET_WALLPAPER
    }





    Card(
            modifier = modifier,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            shape = MaterialTheme.shapes.large
    ) {


        Box() {

            Image(
                  /*  painter = rememberImagePainter(
                            data = imgUri,
                            builder = {
                                crossfade(true)
                                placeholder(R.drawable.loading_animation)
                            }
                    )*/,
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

                //display delete option Assist Chip

                AssistChip(
                        onClick = {


                            onDeletePhoto()
                            isFavorite = !isFavorite
                            //Timber.i("UnMarked True - isFav is: $isFavorite")


                        },
                        colors = AssistChipDefaults.assistChipColors(
                                leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        leadingIcon = {

                            LottieAnimationPlaceHolder(lottie = R.raw.delete_red_icon)
                            /* Icon(
                                     imageVector = Icons.Default.Delete,
                                     contentDescription = stringResource(
                                             id = R.string.delete_photo
                                     )
                             )*/
                        }, label = {
                    Text(text = stringResource(id = R.string.delete_photo))
                }
                )


            } else {
                //display Favourite AssistChip
                AssistChip(
                        onClick = {

                            onMarkAsFavorite()
                            isFavorite = !isFavorite
                            //  Timber.i("Marked True - isFav is: $isFavorite")

                        },
                        colors = AssistChipDefaults.assistChipColors
                        (leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant),
                        leadingIcon = {

                            LottieAnimationPlaceHolder(lottie = R.raw.green_heart_like)
                            /* Icon(
                                     imageVector = Icons.Default.Favorite,
                                     contentDescription = stringResource(R.string.favourite_label)
                             )*/
                        }, label = { Text(text = stringResource(id = R.string.favourite_label)) }
                )
            }
        }


    }

}
@Composable
fun ShareAstroPhoto(uri:String){

    val context = LocalContext.current

    val intent = Intent().apply {

        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_STREAM, uri)
        type = "image/jpg"
    }

    context.startActivity(Intent.createChooser(intent, null))
}