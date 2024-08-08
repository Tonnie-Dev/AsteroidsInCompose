package com.uxstate.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.uxstate.util.LocalSpacing


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <M> PullToRefreshLazyColumn(
    items: List<M>,
    keyExtractor: (M) -> Any,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
    content: @Composable (M) -> Unit
) {

    val spacing = LocalSpacing.current

    val pullToRefreshState = rememberPullToRefreshState()

    //Box Container to connect box to pull refresh state
    // Use nested scroll to communicate scroll information
    // Modify element to make it participate in the nested scrolling hierarchy
    Box(modifier = modifier.nestedScroll(pullToRefreshState.nestedScrollConnection)) {


        LazyColumn(
                state = lazyListState,
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
                contentPadding = WindowInsets.systemBars.asPaddingValues()
        ) {


            items(items = items, key = keyExtractor){

                content(it)
            }

        }
        //listening to refreshing state
        if (pullToRefreshState.isRefreshing){

            //fire a launch block once to call refresh functionality

            LaunchedEffect(key1 = true) {

                onRefresh()

            }

            //listen to is externally passed isRefreshing
            LaunchedEffect(key1 = isRefreshing) {


                if (isRefreshing){

                    pullToRefreshState.startRefresh()
                }else {

                    pullToRefreshState.endRefresh()
                }

            }

        }
        
        //Indicator with some config e.g. alignment
        PullToRefreshContainer(state = pullToRefreshState, modifier = Modifier.align(Alignment.TopCenter))
    }

}