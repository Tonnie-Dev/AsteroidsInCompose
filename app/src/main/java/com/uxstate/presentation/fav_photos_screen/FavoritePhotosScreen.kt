package com.uxstate.presentation.fav_photos_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.R
import com.uxstate.presentation.components.AstroPhotoComposable
import com.uxstate.presentation.destinations.PhotoDetailsScreenDestination
import com.uxstate.util.LocalSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun FavoritePhotosScreen(
    viewModel: FavPhotosViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val spacing = LocalSpacing.current
    val photos = viewModel.state.favoritePhotosList

    Scaffold(topBar = {
      CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                title = {

                    Text(text = stringResource(id = R.string.fav_header))
                }
        )


    }) { paddingValues ->

        if (photos.isNotEmpty()) {
            LazyColumn(contentPadding = paddingValues, content = {

                items(photos) { photo ->

                    AstroPhotoComposable(picture = photo, onTapPhoto = {
                        navigator.navigate(PhotoDetailsScreenDestination(photo = photo))
                    }) {

                    }
                }
            })
        } else {

            Box(modifier = Modifier.fillMaxSize()) {

                Text(
                        text = stringResource(id = R.string.no_fav_photos),
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                )
            }

        }


    }


}