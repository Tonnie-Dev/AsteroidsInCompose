package com.uxstate.presentation.fav_photos_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.R
import com.uxstate.presentation.components.FavPhotoComposable
import com.uxstate.presentation.components.LottieAnimationPlaceHolder
import com.uxstate.presentation.components.NoDataFoundAnimation
import com.uxstate.presentation.components.SelectableBottomItem
import com.uxstate.presentation.destinations.PhotoDetailsScreenDestination
import com.uxstate.util.LocalSpacing
import com.uxstate.util.PhotoDateFilter

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun FavoritePhotosScreen(
    viewModel: FavPhotosViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val spacing = LocalSpacing.current
    val scope = rememberCoroutineScope()

            //val scaffoldState = remembersc
    val photos = viewModel.state.favoritePhotosList

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                title = {

                    Text(text = stringResource(id = R.string.fav_header))
                },
                navigationIcon = {
                    IconButton(onClick = { navigator.navigateUp() }) {
                        Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = stringResource(
                                        id = R.string.back_label
                                ),

                                )
                    }
                }
        )


    }, bottomBar = {

        BottomAppBar(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ) {

            var selectedIndex by remember { mutableStateOf(0) }
            Row(
                    modifier = Modifier
                            .fillMaxWidth()
                            .padding(spacing.spaceExtraSmall),
                    horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                DateItem.values()
                        .forEachIndexed { index, dateItem ->
                            SelectableBottomItem(
                                    isSelected = selectedIndex == index,
                                    text = dateItem.title,
                                    onClick = {
                                        viewModel.onEvent(
                                                event = when (index) {
                                                    0 -> FavoritePhotoScreenEvent.OnClickTodayPhotos(
                                                            PhotoDateFilter.TodayPhotos
                                                    )
                                                    1 -> FavoritePhotoScreenEvent.OnClickRecentPhotos(
                                                            PhotoDateFilter.RecentPhotos
                                                    )
                                                    2 -> FavoritePhotoScreenEvent.OnClickAllPhotos(
                                                            PhotoDateFilter.AllPhotos
                                                    )
                                                    else -> FavoritePhotoScreenEvent.OnClickTodayPhotos(
                                                            PhotoDateFilter.TodayPhotos
                                                    )
                                                }

                                        )
                                        selectedIndex = index


                                    },
                                    icon = dateItem.icon
                            )
                        }

            }


        }
    }) { paddingValues ->

        if (photos.isNotEmpty()) {
            LazyColumn(contentPadding = paddingValues, content = {

                items(photos) { photo ->

                    FavPhotoComposable(
                            modifier = Modifier.padding(spacing.spaceSmall),
                            photo = photo,
                            onTapPhoto = {
                                navigator.navigate(PhotoDetailsScreenDestination(photo = photo))
                            },
                            onDeletePhoto = {

                                viewModel.onEvent(
                                        FavoritePhotoScreenEvent.OnRemoveFromFavorite(photo)
                                )
                            }
                    )
                }
            })
        } else {

NoDataFoundAnimation()

        }


    }


}


