package com.uxstate.presentation.fav_photos_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.uxstate.R
import com.uxstate.presentation.components.AstroShareComposable
import com.uxstate.presentation.viewmodel.OverviewViewModel
import com.uxstate.util.LocalSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun FavoritePhotosScreen(viewModel: OverviewViewModel = hiltViewModel()) {
    val spacing = LocalSpacing.current
    val photos = viewModel.state.astroPhotos
    Scaffold { padding ->


        if (photos.isNotEmpty()) {

            LazyColumn(content = {

                items(photos) { photo ->

                    AstroShareComposable(picture = photo, modifier = Modifier.padding(padding))
                }
            })

            Box(modifier = Modifier.fillMaxHeight()) {

                Text(
                        text = stringResource(id = R.string.no_fav_photos),
                        color = MaterialTheme.colorScheme.error
                )
            }


        }

    }


}