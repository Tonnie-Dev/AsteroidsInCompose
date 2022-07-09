package com.uxstate.presentation.fav_photos_screen

import com.uxstate.domain.model.AstroPhoto
import com.uxstate.util.PhotoDateFilter

sealed class FavoritePhotoScreenEvent {



    data class OnRemoveFromFavorite(val photo:AstroPhoto):FavoritePhotoScreenEvent()
    data class OnClickTodayPhotos(val dateFilter: PhotoDateFilter)
    data class OnClickRecentPhotos(val dateFilter: PhotoDateFilter)
    data class OnClickAllPhotos(val dateFilter: PhotoDateFilter)
}
