package com.uxstate.util

import com.uxstate.domain.model.AstroPhoto


sealed class ViewState {
    // Represents different states for the All Task screen
    data object Empty : ViewState()
    data object Loading : ViewState()
    data class Success(val photos: List<AstroPhoto>) : ViewState()
    data class Error(val exception: Throwable) : ViewState()
}