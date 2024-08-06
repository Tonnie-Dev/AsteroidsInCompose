package com.uxstate.presentation.overview_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.domain.use_cases.UseCaseContainer
import com.uxstate.util.Resource
import com.uxstate.util.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val useCaseContainer: UseCaseContainer
) : ViewModel() {


    private val _state = MutableStateFlow(PhotoState())
    val state = _state.asStateFlow()

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val feed = _viewState.asStateFlow()

    private var getPhotosJob: Job? = null


    init {
        getAstroPictures()
        getLiveAstroPhotos()
    }

    fun onEvent(event: OverviewEvent) {

        when (event) {

            is OverviewEvent.OnRefreshAstroPhoto -> {
                getAstroPictures(fetchFromRemote = true)
                _viewState.value = ViewState.Loading
            }

            is OverviewEvent.OnMarkFavorite -> {

                //update db - insert
                viewModelScope.launch {
                    withContext(IO) {

                        //update DB1
                        useCaseContainer.updateIsFavoriteStatus(event.photo, event.isFavorite)

                        //insert to DB2
                        useCaseContainer.insertAstroPhotoUseCase(event.photo)


                    }


                }


            }

            is OverviewEvent.OnRemoveFromFavorites -> {


                //update db - remove
                viewModelScope.launch {

                    withContext(IO) {

                        //update DB1
                        useCaseContainer.updateIsFavoriteStatus(event.photo, event.isFavorite)

                        //delete from DB2
                        useCaseContainer.deleteFavoritePhotoUseCase(event.photo)

                    }
                }
            }


            is OverviewEvent.OnRetry -> {
                getAstroPictures(fetchFromRemote = true)
                _viewState.value = ViewState.Loading

            }
        }
    }

    private fun getAstroPictures(fetchFromRemote: Boolean = false) {

        getPhotosJob?.cancel()


        getPhotosJob = useCaseContainer.getAstroPhotosUseCase(fetchFromRemote)
                .onEach { result ->

                    when (result) {

                        is Resource.Loading -> {

                            _state.update { it.copy(isPhotosListLoading = result.isLoading) }

                        }
                        is Resource.Error -> {

                            _viewState.value = ViewState.Error(Exception())

                            _state.update { it.copy(errorMessage = result.message) }
                        }
                        is Resource.Success -> {

                            result.data?.let { photos ->

                                _state.update { it.copy(astroPhotos = photos) }

                            }

                        }
                    }


                }
                .launchIn(viewModelScope)

    }

    private fun getLiveAstroPhotos() {

        viewModelScope.launch(IO) {
            useCaseContainer.getLiveAstroPhotosUseCase()
                    .distinctUntilChanged()
                    .collect { result ->
                        try {
                            if (result.isEmpty()) {
                                _viewState.value = ViewState.Empty
                            } else {
                                _viewState.value = ViewState.Success(result)
                            }
                        } catch (e: Exception) {
                            _viewState.value = ViewState.Error(e)
                        }
                    }
        }

    }



}