package com.uxstate.presentation.overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.domain.use_cases.GetAstroPicturesUseCase
import com.uxstate.domain.use_cases.GetNeowsUseCase
import com.uxstate.util.DateFilter
import com.uxstate.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val useCase: GetNeowsUseCase,
    private val getAstroPicturesUseCase: GetAstroPicturesUseCase
) : ViewModel() {


    //by delegate is used because OverviewState has get being a data class
    var state by mutableStateOf(OverviewState())
        private set

    val _uiEvent = Channel<OverviewEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getNearEarthObjects()
        getAstroPictures()
    }

    fun onEvent(event: OverviewEvent) {

        when (event) {

            is OverviewEvent.OnClickTodayButton -> {
                getNearEarthObjects()

            }

            is OverviewEvent.OnClickTomorrowButton -> {

                getNearEarthObjects(
                        startDate = LocalDateTime.now()
                                .plusDays(1),
                        endDate = DateFilter.TomorrowDate
                )
            }

            is OverviewEvent.OnClickNextSevenDaysButton -> {

                getNearEarthObjects(endDate = DateFilter.NextSevenDays, fetchFromRemote = false)
            }

            is OverviewEvent.Refreshing -> {

                getNearEarthObjects(endDate = DateFilter.NextSevenDays, fetchFromRemote = true)
            }
        }
    }

    private fun getNearEarthObjects(
        startDate: LocalDateTime = LocalDateTime.now(),
        endDate: DateFilter = DateFilter.TodayDate,
        fetchFromRemote: Boolean = false
    ) {


        //you can either use flow.collect or flow.onEach
        useCase(
                startDate = startDate,
                endDate = endDate,
                fetchFromRemote = fetchFromRemote
        ).onEach {

            result ->

            when (result) {

                is Resource.Success -> {

                    result.data?.let {

                        state = state.copy(neows = it)
                    }


                }
                is Resource.Error -> {

                    state =
                        state.copy(errorMessage = result.message ?: "An expected error occurred")

                }
                is Resource.Loading -> {

                    state = state.copy(isLoading = result.isLoading)
                }
            }


        }
                .launchIn(viewModelScope)
    }


    private fun getAstroPictures() {

getAstroPicturesUseCase().onEach { result ->


    when(result){


        is Resource.Loading -> {

            state = state.copy(isPictureLoading = result.isLoading)
        }
        is Resource.Error -> {

            state = state.copy(pictureErrorMessage = result.message)
        }
        is Resource.Success -> {

            result.data?.let {

                state =state.copy(astroPictures = it)
            }

        }
    }


}.launchIn(viewModelScope)
    }

}