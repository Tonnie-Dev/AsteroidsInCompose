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
import kotlinx.coroutines.Job
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

    var isFavoriteState by mutableStateOf(false)
    private set
    private var getPhotosJob: Job? = null
    val _uiEvent = Channel<OverviewEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {


        getAstroPictures()


    }

    fun onEvent(event: OverviewEvent) {

        when (event) {

            is OverviewEvent.OnRefreshAstroPhoto -> {
                getAstroPictures(fetchFromRemote = true)


            }

            is OverviewEvent.OnMarkFavorite -> {

                //update db - insert
                viewModelScope.launch {
                    withContext(IO) {



                        //update DB1
                       useCaseContainer.updateIsFavoriteStatus(event.photo)

                        //generate new state
                        state.astroPhotos.find { it.date == event.photo.date }?.isFavorite = true

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
                        useCaseContainer.updateIsFavoriteStatus(event.photo)

                        //generate new state
                       state.astroPhotos.find { it.date == event.photo.date }?.isFavorite = false

                        //delete from DB2
                        useCaseContainer.deleteFavoritePhotoUseCase(event.photo)

                    }
                }
            }


        }
    }


    private fun getAstroPictures(fetchFromRemote: Boolean = false) {

        getPhotosJob?.cancel()


        getPhotosJob =useCaseContainer.getAstroPhotosUseCase(fetchFromRemote)
                .onEach { result ->

                    when (result) {

                        is Resource.Loading -> {

                            state = state.copy(isPhotosListLoading = result.isLoading)
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


    suspend fun isInDatabase(photo: AstroPhoto): Boolean {

        return useCaseContainer.checkIfPhotoIsInDatabaseUseCase(photo)

    }

    private suspend fun updateFavoritePhotos(photo: AstroPhoto, status:Boolean) {

        val currentPhotoId = photo.date


        //update DB
       // useCaseContainer.updateIsFavoriteStatus(currentPhotoId)

        //updateStatus
       // state.astroPhotos.find { it.date == photo.date }?.isFavorite = status


        //Timber.i("Overview stat item 1 is: ${state.astroPhotos[0].isFavorite}")


    }

    fun isFavorite(photo: AstroPhoto):Boolean{


        viewModelScope.launch {

            withContext(IO){
                isFavoriteState = useCaseContainer.checkIfPhotoIsInDatabaseUseCase(photo)
            }
        }



        return isFavoriteState

    }

}