package com.uxstate.presentation.fav_photos_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.domain.model.AstroPhoto
import com.uxstate.domain.use_cases.UseCaseContainer
import com.uxstate.util.PhotoDateFilter
import com.uxstate.util.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavPhotosViewModel @Inject constructor(private val useCaseContainer: UseCaseContainer) :
    ViewModel() {

    private val _stateFlow = MutableStateFlow<ViewState>(ViewState.Loading)
    // The UI collects from this StateFlow to get its state update
    val stateFlow = _stateFlow.asStateFlow()

    var recentlyDeletedPhoto: AstroPhoto? = null


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

                        //insert DB2
                        useCaseContainer.insertAstroPhotoUseCase(
                                recentlyDeletedPhoto ?: return@withContext
                        )
                        //  update DB1
                        useCaseContainer.updateIsFavoriteStatus(
                                recentlyDeletedPhoto ?: return@withContext, true
                        )

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


        viewModelScope.launch {
            useCaseContainer.getFavAstroPhotosUseCase(dateFilter = dateFilter).distinctUntilChanged()
                    .collect { result ->

                        try {


                            if (result.isEmpty()) {
                                _stateFlow.value = ViewState.Empty
                            } else {
                                _stateFlow.value = ViewState.Success(result)
                            }
                        } catch (e: Exception) {

                            _stateFlow.value = ViewState.Error(e)
                        }


                    }

        }


    }


}