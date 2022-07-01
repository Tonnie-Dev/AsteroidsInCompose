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
import kotlinx.coroutines.flow.*
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

    private val _stateFlow = MutableStateFlow(PhotoState())

    val stateFlow = _stateFlow.asStateFlow()

    var isFavoriteState by mutableStateOf(false)
    private set
    private var getPhotosJob: Job? = null
    val _uiEvent = Channel<OverviewEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {


        getAstroPhotosUsingStateFlow()


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
                      state.astroPhotos.find { it.date == event.photo.date }?.isFavorite = isInDatabase(event.photo)


                        //insert to DB2
                        useCaseContainer.insertAstroPhotoUseCase(event.photo)
                        Timber.i("onMarK Fav VM - state isFav ${state.astroPhotos[0].isFavorite}")

                    }


                }


            }

            is OverviewEvent.OnRemoveFromFavorites -> {


                //update db - remove
                viewModelScope.launch {

                    withContext(IO) {

                        //update DB1
                        useCaseContainer.updateIsFavoriteStatus(event.photo)


                        state.astroPhotos.find { it.date == event.photo.date }?.isFavorite =  isInDatabase(event.photo)


                        //delete from DB2
                        useCaseContainer.deleteFavoritePhotoUseCase(event.photo)
                        Timber.i("onRemove Fav VM - state isFav ${state.astroPhotos[0].isFavorite}")

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

     fun getAstroPhotosUsingStateFlow(fetchFromRemote: Boolean = false) =


        viewModelScope.launch {
            useCaseContainer.getAstroPhotosUseCase(fetchFromRemote).collect{

                result ->

                when (result) {

                    is Resource.Loading -> {
                        _stateFlow .value= PhotoState(isPhotosListLoading = result.isLoading)
                    }
                    is Resource.Error -> {

                        _stateFlow .value= PhotoState(errorMessage = result.message)
                    }
                    is Resource.Success -> {



                        result.data?.let {
                            _stateFlow .value= PhotoState(astroPhotos = it)


                        }

                    }
                }



            }


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