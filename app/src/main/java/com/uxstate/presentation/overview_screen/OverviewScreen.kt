package com.uxstate.presentation.overview_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.R
import com.uxstate.presentation.components.AstroPhotoComposable
import com.uxstate.presentation.destinations.FavoritePhotosScreenDestination
import com.uxstate.presentation.destinations.PhotoDetailsScreenDestination
import com.uxstate.util.LocalSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Destination(start = true)
@Composable
fun OverviewScreen(
    viewModel: OverviewViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {

    val state = viewModel.state
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isPhotosListLoading)
    val spacing = LocalSpacing.current


    Scaffold(
            topBar = {
                LargeTopAppBar(
                        colors = TopAppBarDefaults.largeTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                        ),

                        title = { Text(text = stringResource(id = R.string.all_photos)) }
                )
            },


            floatingActionButton = {
                FloatingActionButton(onClick = { navigator.navigate(FavoritePhotosScreenDestination) }) {

                    Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = stringResource(id = R.string.favourite_label)
                    )
                }

            }) { values ->


        Column(
                modifier = Modifier
                        .fillMaxSize()
                        .padding(values)
        ) {


            SwipeRefresh(state = swipeRefreshState, modifier = Modifier
                    .fillMaxWidth()
                    .weight(5f), onRefresh = {

                viewModel.onEvent(OverviewEvent.OnRefreshAstroPhoto)
            }) {

                LazyColumn(content = {


                    items(state.astroPhotos) {

                        AstroPhotoComposable(
                                photo = it,
                                modifier = Modifier.fillMaxWidth(),
                                onTapPhoto = {

                                    navigator.navigate(PhotoDetailsScreenDestination(it))
                                },
                                onMarkAsFavorite = {

                                    viewModel.onEvent(OverviewEvent.OnMarkFavorite(it))
                                },
                                onDeletePhoto = {
                                    viewModel.onEvent(
                                            OverviewEvent.OnRemoveFromFavorites(it)
                                    )
                                })
                        Spacer(modifier = Modifier.height(spacing.spaceMedium))

                    }
                })


            }
        }
    }


}


