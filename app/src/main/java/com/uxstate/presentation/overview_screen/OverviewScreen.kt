package com.uxstate.presentation.overview_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.presentation.destinations.AstroShareScreenDestination
import com.uxstate.presentation.overview_screen.components.AstroPhotoComposable
import com.uxstate.util.LocalSpacing

@Destination(start = true)
@Composable
fun OverviewScreen(
    viewModel: OverviewViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {

    val state = viewModel.state
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isPictureLoading)
    val spacing = LocalSpacing.current

        Column(
                modifier = Modifier
                        .fillMaxSize()
                        .padding(spacing.spaceMedium)
        ) {


            SwipeRefresh(state = swipeRefreshState, modifier = Modifier
                    .fillMaxWidth()
                    .weight(5f), onRefresh = {

                viewModel.onEvent(OverviewEvent.OnRefreshNeows)
            }) {

                LazyColumn(content = {


                    items(state.astroPhotoEntities) {

                        AstroPhotoComposable(picture = it, modifier = Modifier.fillMaxWidth()){

                            navigator.navigate( AstroShareScreenDestination(it))
                        }
                        Spacer(modifier = Modifier.height(spacing.spaceMedium))

                    }
                })


            }
        }




}


