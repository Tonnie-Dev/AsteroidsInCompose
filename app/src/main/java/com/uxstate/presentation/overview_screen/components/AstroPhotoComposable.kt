package com.uxstate.presentation.overview_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.transition.CrossfadeTransition
import com.uxstate.R
import com.uxstate.domain.model.AstroPicture
import com.uxstate.util.LocalSpacing

@OptIn(ExperimentalAnimationGraphicsApi::class)
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



        Box (modifier = modifier){



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
                        .aspectRatio(3f/2f))



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
/*
            Image(
                    
                    painter = rememberAsyncImagePainter(model = imgUri,contentScale = ContentScale.Crop, transform = { CrossfadeTransition}),
                   *//* painter = rememberImagePainter(
                            data = imgUri,
                            builder = {
                                crossfade(true)
                                placeholder(R.drawable.loading_animation)


                            }*//*
                           contentDescription = null,

                    modifier = Modifier
                            .align(Alignment.Center)
                            .size(420.dp, 240.dp)
                            .padding(8.dp)

                    )*/






            /*
             val image = AnimatedImageVector.animatedVectorResource(R.drawable.loading_animation)
    val atEnd by remember { mutableStateOf(true) }

            AsyncImage(
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
          /*  Image(
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
                            .align(Alignment.Center)
                            .size(420.dp, 240.dp)
                            .padding(8.dp)

            )*/




        }


    }




////https://coil-kt.github.io/coil/compose/