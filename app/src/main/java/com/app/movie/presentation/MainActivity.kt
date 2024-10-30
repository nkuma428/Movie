package com.app.movie.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.app.movie.presentation.nav.NavGraph
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main activity of the application.
 * This activity sets up the navigation graph and initializes the Hilt dependency injection.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavGraph(navController = navController)
}