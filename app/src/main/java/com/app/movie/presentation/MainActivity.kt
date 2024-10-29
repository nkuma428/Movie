package com.app.movie.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.app.movie.presentation.movies.viewmodel.MovieListViewModel
import com.app.movie.presentation.nav.NavGraph
import com.app.movie.presentation.theme.MovieTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MovieListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            MovieApp(viewModel)
        }
    }
}

@Composable
fun MovieApp(viewModel : MovieListViewModel) {
    val navController = rememberNavController()
    MaterialTheme {
        Surface {
            NavGraph(navController = navController, viewModel = viewModel)
        }
    }
}