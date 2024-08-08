package com.uxstate.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.ramcosta.composedestinations.DestinationsNavHost


import com.uxstate.presentation.ui.theme.AsteroidsInComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.auto(
                        Color.TRANSPARENT,
                        Color.TRANSPARENT
                ),
                navigationBarStyle = SystemBarStyle.auto(
                        Color.TRANSPARENT,
                        Color.TRANSPARENT
                )
        )
        super.onCreate(savedInstanceState)
        setContent {
            AsteroidsInComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                ) {
                    //add the NavHost call
                    DestinationsNavHost(navGraph = NavGraphs.root)
                }



            }
        }
    }
}

