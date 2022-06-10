package com.uxstate.presentation.overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.presentation.overview.components.NeowsItem
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Destination(start = true)
@Composable
fun OverviewScreen(
    viewModel: OverviewViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {


    val state = viewModel.state
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isRefreshing)


    Column(modifier = Modifier.fillMaxSize()) {

        //IMAGE

        //BUTTON ROW

        SwipeRefresh(state = swipeRefreshState, onRefresh = {

            viewModel.onEvent(OverviewEvent.Refreshing)
        }) {

            LazyColumn(content = {


                items(state.neows){

                    NeowsItem(
                            name = it.codename,
                            approachDate = localDateToString(it.closeApproachDate),
                            isHazardous = it.isPotentiallyHazardous,
                            onClickNeowsItem = { /*TODO*/ })

                }
            })


        }
    }


}

fun localDateToString(date: LocalDate):String {

    val pattern = "dd-MM-yyyy"
    val dateFormatter = DateTimeFormatter.ofPattern(pattern)
    return date.format(dateFormatter)


}
