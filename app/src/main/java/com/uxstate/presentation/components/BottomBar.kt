package com.uxstate.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun BottomBar() {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(elevation = 10.dp) {

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Home,"")
        },
                label = { Text(text = "Home") },
                selected = (selectedIndex.value == 0),
                onClick = {
                    selectedIndex.value = 0
                })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Favorite,"")
        },
                label = { Text(text = "Favorite") },
                selected = (selectedIndex.value == 1),
                onClick = {
                    selectedIndex.value = 1
                })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Person,"")
        },
                label = { Text(text = "Profile") },
                selected = (selectedIndex.value == 2),
                onClick = {
                    selectedIndex.value = 2
                })
    }
}