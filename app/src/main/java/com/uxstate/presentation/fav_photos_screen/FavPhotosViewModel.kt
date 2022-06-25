package com.uxstate.presentation.fav_photos_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.domain.model.AstroPhoto
import com.uxstate.domain.use_cases.UseCaseContainer
import com.uxstate.presentation.overview_screen.OverviewEvent
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
        getSavedPhotos()
    }




    fun onEvent(event: FavoritePhotoScreenEvent){


        when(event){

            is FavoritePhotoScreenEvent.OnRemoveFromFavorite -> {

               //delete from db

               viewModelScope.launch {

                   withContext(IO){

                       useCaseContainer.deleteFavoritePhotoUseCase(event.photo)
                   }
               }

                //remove from the current list
              // state = state.favoritePhotosList.remove(event.photo)
               // getSavedPhotos()
            }
        }
    }
    //get photos

    private fun getSavedPhotos() {


        useCaseContainer.getFavAstroPhotosUseCase()
                .onEach {


                    favPhotos ->
                    state = state.copy(favoritePhotosList = favPhotos?: emptyList())


                }
                .launchIn(viewModelScope)
    }
}