package com.uxstate.presentation.overview_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.R
import com.uxstate.presentation.destinations.AstroShareScreenDestination
import com.uxstate.presentation.components.AstroPhotoComposable
import com.uxstate.presentation.viewmodel.OverviewViewModel
import com.uxstate.util.LocalSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Destination(start = true)
@Composable
fun OverviewScreen(
    viewModel: OverviewViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {

    val state = viewModel.state
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isPhotoLoading)
    val spacing = LocalSpacing.current


    Scaffold(floatingActionButton = { FloatingActionButton(onClick = { /*TODO*/ }) {

        Icon(imageVector = Icons.Default.Favorite, contentDescription = stringResource(id = R.string.favourite_label))
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


                    items(state.astroPhotoEntities) {

                        AstroPhotoComposable(picture = it, modifier = Modifier.fillMaxWidth()){

                            navigator.navigate( AstroShareScreenDestination(it))
                        }
                        Spacer(modifier = Modifier.height(spacing.spaceMedium))

                    }
                })


            }
        }}




}


