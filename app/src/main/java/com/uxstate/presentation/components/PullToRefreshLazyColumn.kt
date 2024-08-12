package com.uxstate.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <M> PullToRefreshLazyColumn(
    items: List<M>,
    keyExtractor: (M) -> Any,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (M) -> Unit
) {

    PullToRefreshBox(modifier = modifier,isRefreshing = isRefreshing, onRefresh = onRefresh) {

        LazyColumn (modifier = Modifier.fillMaxSize()){

            if (isRefreshing.not()){

                items(items = items , key = keyExtractor){

                    content(it)
                }
            }
        }

    }

}