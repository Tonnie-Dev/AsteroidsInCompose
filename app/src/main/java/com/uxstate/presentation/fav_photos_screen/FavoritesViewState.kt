package com.uxstate.presentation.fav_photos_screen

import com.uxstate.domain.model.AstroPhoto



sealed class FavoritesViewState (){
    // Represents different states for the All Task screen
    object Empty : FavoritesViewState()
    object Loading : FavoritesViewState()
    data class Success(val photos: List<AstroPhoto>) : FavoritesViewState()
    data class Error(val exception: Throwable) : FavoritesViewState()
}
