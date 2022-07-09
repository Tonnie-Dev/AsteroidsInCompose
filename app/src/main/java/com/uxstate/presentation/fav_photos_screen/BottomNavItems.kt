package com.uxstate.presentation.fav_photos_screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesomeMotion
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Image
import androidx.compose.ui.graphics.vector.ImageVector

enum class DateItem( val title: String, val icon: ImageVector) {
   TODAY("Today", Icons.Default.Image),RECENT("Recent", Icons.Default.Collections), ALL("All",Icons.Default.AutoAwesomeMotion)

  /*  object Today : DateItem("Today", Icons.Default.Image)
    object Recent : DateItem("Recent", Icons.Default.Collections)
    object All:DateItem("All",Icons.Default.AutoAwesomeMotion)*/

}

