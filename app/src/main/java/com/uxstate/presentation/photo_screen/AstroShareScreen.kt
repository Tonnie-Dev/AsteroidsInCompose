package com.uxstate.presentation.photo_screen

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.uxstate.domain.model.AstroPicture
import com.uxstate.presentation.photo_screen.components.AstroShareComposable

@Destination
@Composable
fun AstroShareScreen(astroPicture: AstroPicture) {
    
    AstroShareComposable(picture = astroPicture)
}