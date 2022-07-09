package com.uxstate.presentation.detail_screen

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import androidx.core.net.toUri
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ramcosta.composedestinations.annotation.Destination
import com.uxstate.R
import com.uxstate.domain.model.AstroPhoto
import com.uxstate.util.LocalSpacing
import java.io.File
import java.io.FileOutputStream


@ExperimentalMaterial3Api

@Destination
@Composable
fun PhotoDetailsScreen(
    photo: AstroPhoto,
    modifier: Modifier = Modifier
) {

    val spacing = LocalSpacing.current

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




    Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxHeight()
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

            Row(
                    horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.align(
                    Alignment.BottomStart
            )
            ) {

                val context = LocalContext.current
                AssistChip(onClick = { shareAstroPhoto("", context) },
                        colors = AssistChipDefaults.assistChipColors(leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant),
                        leadingIcon = {
                            Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = stringResource(id = R.string.favourite_label)
                            )
                        },

                        label = { Text(text = stringResource(id = R.string.favourite_label)) }
                )

                AssistChip(
                        onClick = {

                            val state = painter.state as? AsyncImagePainter.State.Success
                            val drawable = state?.result?.drawable
                            if (drawable != null) {
                                context.shareImage(
                                        "Share image via",
                                        drawable,
                                        "filename"
                                )
                            }
                        },
                        colors = AssistChipDefaults.assistChipColors(leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant),
                        leadingIcon = {
                            Icon(
                                    imageVector = Icons.Default.Share,
                                    contentDescription = stringResource(id = R.string.share_label)
                            )
                        },

                        label = { Text(text = stringResource(id = R.string.share_label)) }
                )
            }


        }
    }
}


fun shareAstroPhoto(uri: String, context: Context) {

    val intent = Intent().apply {

        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_STREAM, uri)
        type = "image/jpg"
    }
    context.startActivity(Intent.createChooser(intent, null))
}


fun Context.shareImage(title: String, image: Drawable, filename: String) {
    val file = try {
        val outputFile = File(cacheDir, "$filename.png")
        val outPutStream = FileOutputStream(outputFile)
        image.toBitmap().compress(Bitmap.CompressFormat.PNG, 100, outPutStream)
        outPutStream.flush()
        outPutStream.close()
        outputFile
    } catch (e: Throwable) {

        null
        //return toast(e)
    }
    val uri = file?.toUriCompat(this)



    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        type = "image/png"
        putExtra(Intent.EXTRA_STREAM, uri)
    }
    val chooser = Intent.createChooser(shareIntent, "Share File")
    val resInfoList = this.packageManager.queryIntentActivities(chooser, PackageManager.MATCH_DEFAULT_ONLY)

    for (resolveInfo in resInfoList) {
        val packageName = resolveInfo.activityInfo.packageName
        grantUriPermission(
                packageName,
                uri,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
        )
    }

    startActivity(chooser)

    //startActivity(Intent.createChooser(shareIntent, title))
}

fun File.toUriCompat(context: Context): Uri {
    return FileProvider.getUriForFile(context, context.packageName + ".provider", this)
}
/*fun Context.toast(throwable: Throwable) =
    throwable.message?.let { toast(it) }
        ?: toast(R.string.unknown_error)*/
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}