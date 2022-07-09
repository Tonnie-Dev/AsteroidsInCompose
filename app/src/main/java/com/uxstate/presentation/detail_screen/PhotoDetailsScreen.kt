package com.uxstate.presentation.detail_screen

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import androidx.core.net.toUri
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.R
import com.uxstate.domain.model.AstroPhoto
import com.uxstate.presentation.components.LottieAnimationPlaceHolder
import com.uxstate.util.LocalSpacing
import java.io.File
import java.io.FileOutputStream


@ExperimentalMaterial3Api

@Destination
@Composable
fun PhotoDetailsScreen(
    photo: AstroPhoto, navigator: DestinationsNavigator
) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val caption = stringResource(
            id = R.string.photo_caption,
            photo.title.toUpperCase(locale = Locale.current)
    )
    val imgUri = photo.url.toUri()
            .buildUpon()
            .scheme("https")
            .build()

    val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                    .data(imgUri)
                    .crossfade(true)
                    .placeholder(R.drawable.loading_animation)
                    .build()
    )

    Scaffold(topBar = {

        SmallTopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                ), title = {
            Text(text = stringResource(id = R.string.photo_details))
        }, navigationIcon = {

            IconButton(onClick = { navigator.navigateUp() }) {
                Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_label)
                )
            }

        })
    },

            bottomBar = {
                BottomAppBar(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant

                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth().padding(spacing.spaceExtraSmall)) {

                        AssistChip(
                                onClick = {

                                    val state = painter.state as? AsyncImagePainter.State.Success
                                    val drawable = state?.result?.drawable
                                    if (drawable != null) {
                                        context.shareImage(
                                                "Share image via sx",
                                                drawable,
                                                "filename",
                                                caption
                                        )

                                    }

                                },
                                colors = AssistChipDefaults.assistChipColors
                                (leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant),
                                leadingIcon = {

                                    LottieAnimationPlaceHolder(lottie = R.raw.share_icon_blue)

                                }, label = { Text(text = stringResource(id = R.string.share_label)) })














                    }
                }
            }
    ) {


        paddingValues ->


        Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                        .fillMaxHeight()
                        .padding(
                                paddingValues
                        )
        ) {


            //Image
            Image(
                    painter = painter,
                    contentDescription = photo.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                            .clip(MaterialTheme.shapes.large)
                            .fillMaxWidth()
                            .padding(spacing.spaceSmall)
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

                    Text(text = photo.title, style = MaterialTheme.typography.titleLarge)

                    Spacer(modifier = Modifier.height(spacing.spaceSmall))

                    Text(text = photo.explanation, style = MaterialTheme.typography.bodyMedium)

                    Spacer(modifier = Modifier.height(spacing.spaceSmall))


                }


            }
        }
    }


}


fun Context.shareImage(title: String, image: Drawable, filename: String, caption: String) {
    val file = try {
        val outputFile = File(cacheDir, "$filename.png")
        val outPutStream = FileOutputStream(outputFile)
        image.toBitmap()
                .compress(Bitmap.CompressFormat.PNG, 100, outPutStream)
        outPutStream.flush()
        outPutStream.close()
        outputFile
    } catch (e: Throwable) {
        e.printStackTrace()
        null
        //return toast(e)
    }
    val uri = file?.toUriCompat(this)

    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        type = "image/png"
        putExtra(Intent.EXTRA_STREAM, uri)
        putExtra(Intent.EXTRA_TEXT, caption)
    }
    val chooser = Intent.createChooser(shareIntent, title)
    val resInfoList =
        this.packageManager.queryIntentActivities(chooser, PackageManager.MATCH_DEFAULT_ONLY)

    for (resolveInfo in resInfoList) {
        val packageName = resolveInfo.activityInfo.packageName
        grantUriPermission(
                packageName,
                uri,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
        )
    }

    startActivity(chooser)


}

fun File.toUriCompat(context: Context): Uri {
    return FileProvider.getUriForFile(context, context.packageName + ".provider", this)
}
