package com.uxstate.presentation.overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.domain.use_cases.GetNeowsUseCase
import com.uxstate.util.DateChanger
import com.uxstate.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(private val useCase: GetNeowsUseCase) : ViewModel() {


    var state by  mutableStateOf(OverviewState())
    private set

    val _uiEvent = Channel<OverviewEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getNearEarthObjects()
    }

    fun onEvent(event:OverviewEvent){

        when (event){

            is OverviewEvent.OnClickTodayButton -> {


            }
            is OverviewEvent.OnClickWeeklyButton -> {}
            is OverviewEvent.OnClickMonthlyButton -> {}
            is OverviewEvent.Refreshing -> {}
        }
    }

    private fun getNearEarthObjects(endDate:DateChanger = DateChanger.TodayDate, fetchFromRemote:Boolean = state.isRefreshing){

        //you can either use flow.collect or flow.onEach
        useCase(endDate = endDate, fetchFromRemote = fetchFromRemote).onEach {

             result ->

            when(result){

                is Resource.Success -> {


                }
                is Resource.Error -> {}
                is Resource.Loading -> {}
            }


        }.launchIn(viewModelScope)
    }

}