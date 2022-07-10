package com.uxstate.presentation.fav_photos_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.domain.model.AstroPhoto
import com.uxstate.domain.use_cases.UseCaseContainer
import com.uxstate.util.PhotoDateFilter
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

    var recentDeletedAstroPhoto: AstroPhoto? = null

    init {
        getFavoritePhotos(dateFilter = PhotoDateFilter.AllPhotos)

    }


    fun onEvent(event: FavoritePhotoScreenEvent) {


        when (event) {

            is FavoritePhotoScreenEvent.OnRemoveFromFavorite -> {

                viewModelScope.launch {

                    withContext(IO) {
                       //delete from DB 2
                        useCaseContainer.deleteFavoritePhotoUseCase(event.photo)
                        //  updateAstroPhotos
                        useCaseContainer.updateIsFavoriteStatus(event.photo,false)

                        recentDeletedAstroPhoto = event.photo
                    }


                }
            }

            is FavoritePhotoScreenEvent.OnClickAllPhotos -> {


                getFavoritePhotos(event.dateFilter)
            }
            is FavoritePhotoScreenEvent.OnClickRecentPhotos -> {

                getFavoritePhotos(event.dateFilter)
            }
            is FavoritePhotoScreenEvent.OnClickTodayPhotos-> {
                getFavoritePhotos(event.dateFilter)
            }


        }
    }
    //get photos
    private fun getFavoritePhotos(dateFilter: PhotoDateFilter) {


        useCaseContainer.getFavAstroPhotosUseCase(dateFilter = dateFilter)
                .onEach {
                    favPhotos ->
                    state = state.copy(favoritePhotosList = favPhotos ?: emptyList())

                }
                .launchIn(viewModelScope)
    }





}