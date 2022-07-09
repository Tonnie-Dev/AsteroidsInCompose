package com.uxstate.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uxstate.R
import com.uxstate.domain.model.AstroPhoto
import com.uxstate.util.LocalSpacing


@ExperimentalMaterial3Api


@Composable
fun AstroShareComposable(
    picture: AstroPhoto,
    modifier: Modifier = Modifier
) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current

    val imgUri = picture.url.toUri()
            .buildUpon()
            .scheme("https")
            .build()

    val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(context)
                    .data(imgUri)
                    .placeholder(R.drawable.loading_animation)
                    .build()
    )




    Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxHeight()
    ) {


        //Image
        Image(
                painter = painter,
                contentDescription = picture.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                        .clip(MaterialTheme.shapes.large)
                        .fillMaxWidth()
                        .aspectRatio(3f / 2f)


        )


        Box(modifier = Modifier.fillMaxHeight()) {

            //Column
            Column(
                    modifier = Modifier
                            .padding(spacing.spaceMedium)
                            .verticalScroll(rememberScrollState())
                            .align(Alignment.TopStart)
            ) {

                Text(text = picture.title, style = MaterialTheme.typography.titleLarge)

                Spacer(modifier = Modifier.height(spacing.spaceSmall))

                Text(text = picture.explanation, style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(spacing.spaceSmall))


            }

            Row(
                    horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.align(
                    Alignment.BottomStart
            )
            ) {
                AssistChip(
                        onClick = { /*TODO*/ },
                        colors = AssistChipDefaults.assistChipColors(leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant),
                        leadingIcon = {
                            Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Favorite"
                            )
                        },

                        label = { Text(text = "Mark as Favorite") }
                )

                AssistChip(
                        onClick = { /*TODO*/ },
                        colors = AssistChipDefaults.assistChipColors(leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant),
                        leadingIcon = {
                            Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Share"
                            )
                        },

                        label = { Text(text = "Share with Others") }
                )
            }


        }
    }
}



