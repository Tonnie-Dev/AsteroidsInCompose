package com.uxstate.presentation.overview_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.domain.use_cases.GetAstroPhotosUseCase
import com.uxstate.domain.use_cases.UseCaseContainer
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
    private val useCaseContainer: UseCaseContainer
) : ViewModel() {


    //by delegate is used because OverviewState has get being a data class
    var state by mutableStateOf(PhotoState())
        private set

    val _uiEvent = Channel<OverviewEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {

        getAstroPictures()
    }

    fun onEvent(event: OverviewEvent) {

        when (event) {


            is OverviewEvent.OnRefreshAstroPhoto -> {
                getAstroPictures()

            }

           is OverviewEvent.OnMarkFavorite -> {

               state = state.copy(isPhotoFavorite = true)
           }

            else -> Unit
        }
    }


    private fun getAstroPictures() {

useCaseContainer.getAstroPhotosUseCase().onEach { result ->

    when(result){

        is Resource.Loading -> {

            state = state.copy(isPhotoLoading = result.isLoading)
        }
        is Resource.Error -> {

            state = state.copy(errorMessage = result.message)
        }
        is Resource.Success -> {

            result.data?.let {

                state =state.copy(astroPhotoEntities = it)
            }

        }
    }


}.launchIn(viewModelScope)
    }

}