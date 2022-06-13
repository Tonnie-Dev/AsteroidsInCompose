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
import com.uxstate.presentation.overview_screen.components.AstroPhotoComposable
import com.uxstate.presentation.overview_screen.components.ButtonItem
import com.uxstate.presentation.overview_screen.components.NeowsItem
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
    val pictureSwipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isPictureLoading)


    /*    LaunchedEffect(key1 = true, block = {

            viewModel.uiEvent.collect{ event ->

                when(event){

                    is OverviewEvent.Refreshing -> {

                        viewModel.onEvent(event = event)
                    }
                    is OverviewEvent.OnClickTodayButton-> {

                        viewModel.onEvent(event = event)
                    }

                    is OverviewEvent.OnClickWeeklyButton -> {
                        viewModel.onEvent(event = event)

                    }
                    is OverviewEvent.OnClickMonthlyButton -> {

                        viewModel.onEvent(event = event)
                    }

                }
            }


        })*/
    Column(
            modifier = Modifier
                    .fillMaxSize()

                    .padding(8.dp)
    ) {

        //IMAGE

        SwipeRefresh(
                state = pictureSwipeRefreshState,
                onRefresh = { viewModel.onEvent(OverviewEvent.OnRefreshAstroPhoto) },
                modifier = Modifier.weight(5f)
        ) {
            LazyRow(
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                    content = {

                        items(state.astroPictures) {

                            AstroPhotoComposable(picture = it, modifier = Modifier.fillMaxSize(),state.isPhotoTapped){

                                viewModel.onEvent(OverviewEvent.OnTapPhoto)
                            }
                        }
                    })
        }




        //BUTTON ROW

        Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
        ) {


            ButtonItem(
                    onClickButton = {
                        viewModel.onEvent(
                                OverviewEvent.OnClickTodayButton

                        )
                    },
                    text = "Today",
                    modifier = Modifier.weight(1f)
            )
            ButtonItem(
                    onClickButton = { viewModel.onEvent(OverviewEvent.OnClickTomorrowButton) },
                    text = "Tomorrow",
                    modifier = Modifier.weight(1f)
            )
            ButtonItem(
                    onClickButton = { viewModel.onEvent(OverviewEvent.OnClickNextSevenDaysButton) },
                    text = "Next 7 Days",
                    modifier = Modifier.weight(1f)
            )

        }

        SwipeRefresh(state = swipeRefreshState, modifier = Modifier
                .fillMaxWidth()
                .weight(4f), onRefresh = {

            viewModel.onEvent(OverviewEvent.OnRefreshNeows)
        }) {

            LazyColumn(content = {


                items(state.neows) {

                    NeowsItem(
                            modifier = Modifier.fillMaxWidth(),
                            name = it.codename,
                            approachDate = localDateToString(it.closeApproachDate),
                            isHazardous = it.isPotentiallyHazardous,
                            onClickNeowsItem = { /*TODO*/ })

                }
            })


        }
    }


}

fun localDateToString(date: LocalDate): String {

    val pattern = "dd-MM-yyyy"
    val dateFormatter = DateTimeFormatter.ofPattern(pattern)
    return date.format(dateFormatter)


}
