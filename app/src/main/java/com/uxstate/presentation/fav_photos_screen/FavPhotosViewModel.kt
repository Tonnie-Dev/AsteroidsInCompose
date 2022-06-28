package com.uxstate.presentation.fav_photos_screen

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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavPhotosViewModel @Inject constructor(private val useCaseContainer: UseCaseContainer) :
    ViewModel() {

    var state by mutableStateOf(FavPhotosState())
        private set

    init {
        getFavoritePhotos()
        //getSavedAstroPhotos()
    }


    fun onEvent(event: FavoritePhotoScreenEvent) {


        when (event) {

            is FavoritePhotoScreenEvent.OnRemoveFromFavorite -> {

                //delete from db

                viewModelScope.launch {

                    withContext(IO) {
                       //delete from DB 2
                        useCaseContainer.deleteFavoritePhotoUseCase(event.photo)
                        //  updateAstroPhotos(event.photo)
                        useCaseContainer.updateIsFavoriteStatus(event.photo)
                        // useCaseContainer.insertAstroPhotoUseCase(event.photo)

                    }


                }

                //remove from the current list
                // state = state.favoritePhotosList.remove(event.photo)
                // getSavedPhotos()
            }
        }
    }
    //get photos

    private fun getFavoritePhotos() {


        useCaseContainer.getFavAstroPhotosUseCase()
                .onEach {


                    favPhotos ->
                    state = state.copy(favoritePhotosList = favPhotos ?: emptyList())


                }
                .launchIn(viewModelScope)
    }


    private fun getSavedAstroPhotos() {


        useCaseContainer.getAstroPhotosUseCase(false)
                .onEach {

                    result ->

                    when (result) {


                        is Resource.Success -> {

                            result.data?.let {

                                state = state.copy(astroPhotos = it)
                            }

                        }
                        else -> Unit
                    }


                }
                .launchIn(viewModelScope)
    }


    /* private fun updateAstroPhotos(photo: AstroPhoto) {



         state.astroPhotos.find { it.date == photo.date }?.isFavorite = false
         Timber.i("update FavStatus = ${state.astroPhotos[0].isFavorite}")
        // Timber.i("The changed object is${state.astroPhotos.find { it.date == photo.date }?.isFavorite = false}")

     }*/


    private fun toggleIsFavoriteStatus(photo: AstroPhoto) {

        val currentPhotoId = photo.date

        viewModelScope.launch {

            withContext(IO) {

                //useCaseContainer.updateIsFavoriteStatus(currentPhotoId)
            }


        }

    }


}