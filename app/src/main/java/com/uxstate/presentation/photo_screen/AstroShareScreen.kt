package com.uxstate.presentation.photo_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.domain.model.AstroPhotoEntity
import com.uxstate.presentation.photo_screen.components.AstroShareComposable
import com.uxstate.util.LocalSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun AstroShareScreen(astroPhotoEntity: AstroPhotoEntity, navigator: DestinationsNavigator) {

    val spacing = LocalSpacing.current
    AstroShareComposable(picture = astroPhotoEntity, modifier = Modifier.padding(spacing.spaceExtraSmall))
}