package com.uxstate.presentation.overview

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.uxstate.domain.use_cases.GetNeowsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(useCase: GetNeowsUseCase) : ViewModel() {


    var state =  mutableStateOf(OverviewState())
    private set

    val _uiEvent = Channel<OverviewEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

}