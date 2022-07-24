package com.uxstate.util

import com.uxstate.domain.model.AstroPhoto


sealed class ViewState {
    // Represents different states for the All Task screen
    object Empty : ViewState()
    object Loading : ViewState()
    data class Success(val photos: List<AstroPhoto>) : ViewState()
    data class Error(val exception: Throwable) : ViewState()
}