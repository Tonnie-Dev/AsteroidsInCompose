package com.uxstate.presentation.overview_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Help
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.R
import com.uxstate.presentation.components.AstroPhotoComposable
import com.uxstate.presentation.components.LoadingAnimation
import com.uxstate.presentation.components.NoConnectionAnimation
import com.uxstate.presentation.components.NoDataFoundAnimation
import com.uxstate.presentation.destinations.AboutScreenDestination
import com.uxstate.presentation.destinations.FavoritePhotosScreenDestination
import com.uxstate.presentation.destinations.PhotoDetailsScreenDestination
import com.uxstate.util.LocalSpacing
import com.uxstate.util.ViewState
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Destination(start = true)
@Composable
fun OverviewScreen(
    viewModel: OverviewViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {

    val feed = viewModel.feed.collectAsState().value
    val state = viewModel.state
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isPhotosListLoading)
    val spacing = LocalSpacing.current


    Scaffold(
            topBar = {
                SmallTopAppBar(
modifier = Modifier.padding(spacing.spaceSmall),
                        title = { Text(text = stringResource(id = R.string.all_photos)) },
                        actions = {

                            IconButton(onClick = { navigator.navigate(AboutScreenDestination) }) {
                                Icon(
                                        imageVector = Icons.AutoMirrored.Filled.Help,
                                        contentDescription = "",
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        },
                        colors = TopAppBarDefaults.largeTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                        ),


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


        when (feed) {

            is ViewState.Success -> {

                SwipeRefresh(state = swipeRefreshState, modifier = Modifier
                        .fillMaxWidth(), onRefresh = {

                    viewModel.onEvent(OverviewEvent.OnRefreshAstroPhoto)
                }) {


                    LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = values,
                            content = {


                                itemsIndexed(feed.photos) { i, item ->
                                    //  Timber.i("At Index i $i item is: ${item.isFavorite}")
                                    AstroPhotoComposable(
                                            photo = item,


                                            modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(spacing.spaceSmall),
                                            onTapPhoto = {

                                                navigator.navigate(
                                                        PhotoDetailsScreenDestination(
                                                                item
                                                        )
                                                )
                                            },
                                            onMarkAsFavorite = {
                                                viewModel.onEvent(
                                                        OverviewEvent.OnMarkFavorite(
                                                                item,
                                                                true
                                                        )
                                                )
                                                Timber.i("onMarkAsFav At Position $i isFav is: ${viewModel.state.astroPhotos[0].isFavorite}")
                                            },
                                            onDeletePhoto = {
                                                viewModel.onEvent(
                                                        OverviewEvent.OnRemoveFromFavorites(
                                                                item,
                                                                false
                                                        )
                                                )

                                                Timber.i("onDeletePhoto At Position $i isFav is: ${viewModel.state.astroPhotos[0].isFavorite}")
                                            })
                                    Spacer(modifier = Modifier.height(spacing.spaceMedium))

                                }
                            })


                }
            }

            is ViewState.Empty -> {
                NoDataFoundAnimation()

            }

            is ViewState.Error -> {

                NoConnectionAnimation() {
                    viewModel.onEvent(OverviewEvent.OnRetry)

                }

            }
            is ViewState.Loading -> {

                LoadingAnimation()

            }


        }


    }


}


