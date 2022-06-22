package com.uxstate.presentation.fav_photos_screen

import com.uxstate.domain.model.AstroPhoto

sealed class FavoritePhotoScreenEvent {



    data class OnRemoveFromFavorite(val photo:AstroPhoto):FavoritePhotoScreenEvent()
}
