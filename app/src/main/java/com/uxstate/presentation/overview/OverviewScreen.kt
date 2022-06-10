package com.uxstate.presentation.overview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.R
import com.uxstate.presentation.overview.components.ButtonItem
import com.uxstate.presentation.overview.components.NeowsItem
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

        Box(
                modifier = Modifier

                        .weight(3f)
        ) {

            Image(
                    painter = painterResource(id = R.drawable.asteroid_hazardous),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
            )
        }

        //BUTTON ROW

        Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
        ) {
            ButtonItem(onClickButton = { viewModel.onEvent(OverviewEvent.OnClickTodayButton) }, text = "Today")
            ButtonItem(onClickButton = { viewModel.onEvent(OverviewEvent.OnClickTomorrowButton) }, text = "Tomorrow")
            ButtonItem(onClickButton = { viewModel.onEvent(OverviewEvent.OnClickNextSevenDaysButton) }, text = "Next 7 Days")

        }

        SwipeRefresh(state = swipeRefreshState, modifier = Modifier
                .fillMaxWidth()
                .weight(6f), onRefresh = {

            viewModel.onEvent(OverviewEvent.Refreshing)
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
