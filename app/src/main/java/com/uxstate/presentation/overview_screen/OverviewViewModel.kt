package com.uxstate.presentation.overview_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.domain.model.AstroPhoto
import com.uxstate.domain.use_cases.UseCaseContainer
import com.uxstate.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
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

                //update UI
               // updateFavoritePhotos(event.photo,isInDatabase(event.photo))


                //update db - insert
                viewModelScope.launch {
                    withContext(IO) {
                        useCaseContainer.insertAstroPhotoUseCase(event.photo)
                        updateFavoritePhotos(event.photo)
                    }

                    Timber.i("OnMark Favorite called - status is:  ${isInDatabase(event.photo)}")
                }


            }

            is OverviewEvent.OnRemoveFromFavorites -> {





                //update db - remove
                viewModelScope.launch {


                    Timber.i("OnRemove called - status is:  ${isInDatabase(event.photo)}")

                    withContext(IO) {

                        useCaseContainer.deleteFavoritePhotoUseCase(event.photo)

                        //update UI
                        // updateFavoritePhotos(event.photo,isInDatabase(event.photo))
                        updateFavoritePhotos(event.photo)
                    }
                }
            }


        }
    }


    private fun getAstroPictures() {

        useCaseContainer.getAstroPhotosUseCase()
                .onEach { result ->

                    when (result) {

                        is Resource.Loading -> {

                            state = state.copy(isPhotoLoading = result.isLoading)
                        }
                        is Resource.Error -> {

                            state = state.copy(errorMessage = result.message)
                        }
                        is Resource.Success -> {

                            result.data?.let {

                                state = state.copy(astroPhotos = it)
                            }

                        }
                    }


                }
                .launchIn(viewModelScope)
    }

    private suspend fun isInDatabase(photo: AstroPhoto): Boolean {


        return  useCaseContainer.checkIfPhotoIsInDatabaseUseCase(photo)



    }

   private suspend fun updateFavoritePhotos(photo: AstroPhoto) {

        state.astroPhotos.find { it.date == photo.date }?.isFavorite = isInDatabase(photo)

    }

}