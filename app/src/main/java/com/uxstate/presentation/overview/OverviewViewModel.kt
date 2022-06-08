package com.uxstate.presentation.overview

import androidx.lifecycle.ViewModel
import com.uxstate.domain.use_cases.GetNeowsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(useCase: GetNeowsUseCase) : ViewModel() {
}