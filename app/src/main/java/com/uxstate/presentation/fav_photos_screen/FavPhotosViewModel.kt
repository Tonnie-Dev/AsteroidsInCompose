package com.uxstate.presentation.fav_photos_screen

import androidx.lifecycle.ViewModel
import com.uxstate.domain.use_cases.UseCaseContainer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavPhotosViewModel @Inject constructor(useCaseContainer: UseCaseContainer) : ViewModel() {


    init {

    }
}