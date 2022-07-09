package com.uxstate.presentation.overview_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
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


        when(val result = viewModel.feed.collectAsState().value){

            is ViewState.Success -> {

                SwipeRefresh(state = swipeRefreshState, modifier = Modifier
                        .fillMaxWidth()
                        , onRefresh = {

                    viewModel.onEvent(OverviewEvent.OnRefreshAstroPhoto)
                }) {



                    LazyColumn(modifier = Modifier.fillMaxWidth(), contentPadding = values, content = {




                        itemsIndexed(result.photos) { i, item->
                            //  Timber.i("At Index i $i item is: ${item.isFavorite}")
                            AstroPhotoComposable(
                                    photo = item,


                                    modifier = Modifier.fillMaxWidth().padding(spacing.spaceSmall),
                                    onTapPhoto = {

                                        navigator.navigate(PhotoDetailsScreenDestination(item))
                                    },
                                    onMarkAsFavorite = {
                                        viewModel.onEvent(OverviewEvent.OnMarkFavorite(item, true))
                                        Timber.i("onMarkAsFav At Position $i isFav is: ${viewModel.state.astroPhotos[0].isFavorite}")
                                    },
                                    onDeletePhoto = {
                                        viewModel.onEvent(
                                                OverviewEvent.OnRemoveFromFavorites(item, false)
                                        )

                                        Timber.i("onDeletePhoto At Position $i isFav is: ${viewModel.state.astroPhotos[0].isFavorite}")
                                    })
                            Spacer(modifier = Modifier.height(spacing.spaceMedium))

                        }
                    })


                }
            }

            is ViewState.Empty -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){


                    Text(text = "NO PHOTOS FOUND")
                }

            }

            is ViewState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){


                    Text(text = "ERROR")
                }

            }

            else -> Unit

        }
/*
        Column(
                modifier = Modifier
                        .fillMaxSize()
                        .padding(values)
        ) {

            if (state.astroPhotos.isEmpty()){

                Box(modifier = Modifier.fillMaxSize()){


                    Text(text = "NO PHOTOS FOUND")
                }
            }else{


            SwipeRefresh(state = swipeRefreshState, modifier = Modifier
                    .fillMaxWidth()
                    .weight(5f), onRefresh = {

                viewModel.onEvent(OverviewEvent.OnRefreshAstroPhoto)
            }) {



                LazyColumn(content = {




                    itemsIndexed(state.astroPhotos) {i, item->
                      //  Timber.i("At Index i $i item is: ${item.isFavorite}")
                        AstroPhotoComposable(
                                photo = item,


                                modifier = Modifier.fillMaxWidth(),
                                onTapPhoto = {

                                    navigator.navigate(PhotoDetailsScreenDestination(item))
                                },
                                onMarkAsFavorite = {
                                    viewModel.onEvent(OverviewEvent.OnMarkFavorite(item, true))
                                    Timber.i("onMarkAsFav At Position $i isFav is: ${viewModel.state.astroPhotos[0].isFavorite}")
                                },
                                onDeletePhoto = {
                                    viewModel.onEvent(
                                            OverviewEvent.OnRemoveFromFavorites(item, false)
                                    )

                                    Timber.i("onDeletePhoto At Position $i isFav is: ${viewModel.state.astroPhotos[0].isFavorite}")
                                })
                        Spacer(modifier = Modifier.height(spacing.spaceMedium))

                    }
                })


            }}
        }*/


    }


}


