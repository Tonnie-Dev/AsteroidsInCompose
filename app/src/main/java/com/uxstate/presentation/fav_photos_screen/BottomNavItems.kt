package com.uxstate.presentation.fav_photos_screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesomeMotion
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Image
import androidx.compose.ui.graphics.vector.ImageVector

sealed class DateItem( val title: String, val icon: ImageVector) {
    object Today : DateItem("Today", Icons.Default.Image)
    object Recent : DateItem("Recent", Icons.Default.Collections)
    object All:DateItem("All",Icons.Default.AutoAwesomeMotion)

}