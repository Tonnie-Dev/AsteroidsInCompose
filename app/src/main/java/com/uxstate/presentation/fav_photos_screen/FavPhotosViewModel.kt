package com.uxstate.presentation.fav_photos_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.domain.model.AstroPhoto
import com.uxstate.domain.use_cases.UseCaseContainer
import com.uxstate.util.PhotoDateFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavPhotosViewModel @Inject constructor(private val useCaseContainer: UseCaseContainer) :
    ViewModel() {

    var state by mutableStateOf(FavPhotosState())
        private set

    var recentlyDeletedPhoto: AstroPhoto? = null

    var photoJob: Job? = null

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
                        useCaseContainer.updateIsFavoriteStatus(event.photo, false)

                        recentlyDeletedPhoto = event.photo
                    }


                }
            }

            is FavoritePhotoScreenEvent.OnRestoreAstroPhoto -> {

                viewModelScope.launch {

                    withContext(IO) {

                        useCaseContainer.insertAstroPhotoUseCase(recentlyDeletedPhoto ?: return@withContext)
                        //  updateAstroPhotos
                        useCaseContainer.updateIsFavoriteStatus(recentlyDeletedPhoto ?: return@withContext, true)

                        //invalidate photo
                        recentlyDeletedPhoto = null
                    }
                }


            }

            is FavoritePhotoScreenEvent.OnClickAllPhotos -> {

                getFavoritePhotos(event.dateFilter)
            }
            is FavoritePhotoScreenEvent.OnClickRecentPhotos -> {

                getFavoritePhotos(event.dateFilter)
            }
            is FavoritePhotoScreenEvent.OnClickTodayPhotos -> {
                getFavoritePhotos(event.dateFilter)
            }




        }
    }

    //get photos
    private fun getFavoritePhotos(dateFilter: PhotoDateFilter) {


        photoJob?.cancel()

       photoJob = useCaseContainer.getFavAstroPhotosUseCase(dateFilter = dateFilter)
                .onEach { favPhotos ->
                    state = state.copy(favoritePhotosList = favPhotos ?: emptyList())

                }
                .launchIn(viewModelScope)
    }


}