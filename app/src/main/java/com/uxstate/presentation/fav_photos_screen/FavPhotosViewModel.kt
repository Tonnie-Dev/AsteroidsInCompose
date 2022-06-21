package com.uxstate.presentation.fav_photos_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.domain.use_cases.UseCaseContainer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavPhotosViewModel @Inject constructor(private val useCaseContainer: UseCaseContainer) :
    ViewModel() {

    var state by mutableStateOf(FavPhotosState())
        private set

    init {
        getSavedPhotos()
    }


    //get photos

    private fun getSavedPhotos() {


        useCaseContainer.getFavAstroPhotosUseCase()
                .onEach {


                    favPhotos ->
                    state = state.copy(favoritePhotosList = favPhotos ?: emptyList())


                }
                .launchIn(viewModelScope)
    }
}