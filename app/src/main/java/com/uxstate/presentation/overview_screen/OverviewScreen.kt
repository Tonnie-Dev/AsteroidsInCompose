package com.uxstate.presentation.overview_screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.Direction
import com.uxstate.R
import com.uxstate.domain.model.AstroPhoto
import com.uxstate.presentation.components.AstroPhotoComposable
import com.uxstate.presentation.components.LoadingAnimation
import com.uxstate.presentation.components.NoConnectionAnimation
import com.uxstate.presentation.components.NoDataFoundAnimation
import com.uxstate.presentation.components.PullToRefreshLazyColumn
import com.uxstate.presentation.destinations.AboutScreenDestination
import com.uxstate.presentation.destinations.DirectionDestination
import com.uxstate.presentation.destinations.FavoritePhotosScreenDestination
import com.uxstate.presentation.destinations.PhotoDetailsScreenDestination
import com.uxstate.presentation.ui.theme.AsteroidsInComposeTheme
import com.uxstate.util.LocalSpacing
import com.uxstate.util.ViewState
import java.util.UUID

@Destination(start = true)
@Composable
fun OverviewScreen(
    viewModel: OverviewViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {

    val state by viewModel.state.collectAsState()
    val viewState by viewModel.viewState.collectAsState()
    OverviewScreen(
            viewState = viewState,
            state = state,
            onClickActionIcon = { navigator.navigate(it) },
            onClickPhoto = { navigator.navigate(it) },
            onClickActionIconFavs = { navigator.navigate(it) },
            onEvent = viewModel::onEvent
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverviewScreen(
    viewState: ViewState,
    state: PhotoState,
    onClickActionIcon: (DirectionDestination) -> Unit,
    onClickPhoto: (Direction) -> Unit,
    onClickActionIconFavs: (DirectionDestination) -> Unit,
    onEvent: (OverviewEvent) -> Unit

) {
    val spacing = LocalSpacing.current

    Scaffold(

            floatingActionButton = {
                FloatingActionButton(onClick = {
                    onClickActionIconFavs(
                            FavoritePhotosScreenDestination
                    )
                }) {

                    Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = stringResource(id = R.string.favourite_label)
                    )
                }

            }) { values ->


        when (viewState) {

            is ViewState.Success -> {

                PullToRefreshLazyColumn(
                      items = viewState.photos,
                        keyExtractor = { it.id },
                        isRefreshing = state.isPhotosListLoading,
                        onRefresh = { onEvent(OverviewEvent.OnRefreshAstroPhoto) })
                { item ->
                    AstroPhotoComposable(
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(spacing.spaceSmall),
                            photo = item,
                            onTapPhoto = {
                                onClickPhoto(PhotoDetailsScreenDestination(item))

                            },
                            onMarkAsFavorite = {
                                onEvent(OverviewEvent.OnMarkFavorite(item, true))
                            },
                            onDeletePhoto = {
                                onEvent(OverviewEvent.OnMarkFavorite(item, false))
                            })
                }


            }

            is ViewState.Empty -> {
                NoDataFoundAnimation()

            }

            is ViewState.Error -> {

                NoConnectionAnimation() {

                    onEvent(OverviewEvent.OnRetry)
                }

            }

            is ViewState.Loading -> {

                LoadingAnimation(modifier = Modifier.padding(values))

            }


        }


    }

}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@PreviewLightDark
@Composable
private fun OverviewScreenLoadingPreview() {
    AsteroidsInComposeTheme {

        Surface {
            OverviewScreen(
                    viewState = ViewState.Loading,
                    state = PhotoState(),
                    onClickActionIcon = {},
                    onClickPhoto = {},
                    onClickActionIconFavs = {}
            ) {

            }
        }
    }
}


@Preview(showBackground = true)
@PreviewLightDark
@Composable
private fun OverviewScreenEmptyPreview() {
    AsteroidsInComposeTheme {

        Surface {
            OverviewScreen(
                    viewState = ViewState.Empty,
                    state = PhotoState(),
                    onClickActionIcon = {},
                    onClickPhoto = {},
                    onClickActionIconFavs = {}
            ) {

            }
        }
    }
}


@Preview(showBackground = true)
@PreviewLightDark
@Composable
private fun OverviewScreenErrorPreview() {
    AsteroidsInComposeTheme {

        Surface {
            OverviewScreen(
                    viewState = ViewState.Error(Exception("Error, please try again")),
                    state = PhotoState(),
                    onClickActionIcon = {},
                    onClickPhoto = {},
                    onClickActionIconFavs = {}
            ) {

            }
        }
    }
}

@Preview(showBackground = true)
@PreviewLightDark
@Composable
private fun OverviewScreenSuccessPreview() {
    AsteroidsInComposeTheme {

        Surface {
            OverviewScreen(
                    viewState = ViewState.Success(photos = generatePhotos()),
                    state = PhotoState(
                            astroPhotos = generatePhotos(10),
                            isPhotosListLoading = false
                    ),
                    onClickActionIcon = {},
                    onClickPhoto = {},
                    onClickActionIconFavs = {}
            ) {

            }
        }
    }


}


private fun generatePhotos(count: Int = 10): List<AstroPhoto> {

    return buildList {

        repeat(count) {

            val isEven = it % 2 == 0
            add(
                    AstroPhoto(
                            id = UUID.randomUUID()
                                    .toString(),
                            title = generateLoremIpsum(5),
                            explanation = generateLoremIpsum(30),
                            mediaType = "image",
                            url = "android.resource://com.uxstate/drawable/asteroid_safe",
                            timeStamp = System.currentTimeMillis(),
                            isFavorite = isEven
                    )
            )
        }
    }
}

private fun generateLoremIpsum(length: Int): String {
    val words = listOf(
            "lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit",
            "sed", "do", "eiusmod", "tempor", "incididunt", "ut", "labore", "et", "dolore",
            "magna", "aliqua", "ut", "enim", "ad", "minim", "veniam", "quis", "nostrud",
            "exercitation", "ullamco", "laboris", "nisi", "ut", "aliquip", "ex", "ea", "commodo",
            "consequat", "duis", "aute", "irure", "dolor", "in", "reprehenderit", "in", "voluptate",
            "velit", "esse", "cillum", "dolore", "eu", "fugiat", "nulla", "pariatur", "excepteur",
            "sint", "occaecat", "cupidatat", "non", "proident", "sunt", "in", "culpa", "qui",
            "officia", "deserunt", "mollit", "anim", "id", "est", "laborum"
    )

    return (1..length).joinToString(" ") { words.random() }
}










