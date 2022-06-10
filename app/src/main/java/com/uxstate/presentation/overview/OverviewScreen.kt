package com.uxstate.presentation.overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun OverviewScreen(viewModel: OverviewViewModel = hiltViewModel(), navigator: DestinationsNavigator) {


    val state = viewModel.state


    Column(modifier = Modifier.fillMaxSize()) {

    }

}