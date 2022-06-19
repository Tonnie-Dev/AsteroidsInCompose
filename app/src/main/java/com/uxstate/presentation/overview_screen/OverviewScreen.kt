package com.uxstate.presentation.overview_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.presentation.destinations.AstroShareScreenDestination
import com.uxstate.presentation.overview_screen.components.AstroPhotoComposable
import com.uxstate.presentation.overview_screen.components.ButtonItem
import com.uxstate.presentation.overview_screen.components.NeowsItem
import com.uxstate.presentation.photo_screen.AstroShareScreen
import com.uxstate.presentation.ui.theme.AsteroidsInComposeTheme
import com.uxstate.util.LocalSpacing
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Destination(start = true)
@Composable
fun OverviewScreen(
    viewModel: OverviewViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {

    val state = viewModel.state
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isRefreshing)
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


                    items(state.astroPictures) {

                        AstroPhotoComposable(picture = it, modifier = Modifier.fillMaxWidth()){

                            navigator.navigate( AstroShareScreenDestination(it))
                        }
                        Spacer(modifier = Modifier.height(spacing.spaceMedium))

                    }
                })


            }
        }




}


