package com.app.movie.presentation.movies.listing

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.movie.R
import com.app.movie.presentation.movies.viewmodel.MovieListViewModel
import com.app.movie.presentation.widgets.ErrorText
import com.app.movie.util.AppConstants
import com.app.movie.util.MovieUtils.Companion.loadFavorites
import com.app.movie.util.MovieUtils.Companion.toggleFavorite
import com.app.movie.util.UiState
import com.google.gson.Gson

/**
 * Composable function to display the movie list screen.
 *
 * @param navController The navigation controller to handle navigation actions.
 * @param viewModel The ViewModel that provides the movie list data.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(navController: NavController, viewModel: MovieListViewModel = hiltViewModel()) {

    val uiState = viewModel.movieResponse.collectAsState()
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("movie_prefs", Context.MODE_PRIVATE)

    // Load favorite movies from SharedPreferences
    val favoriteMovies = remember { mutableStateOf(loadFavorites(sharedPreferences)) }

    Scaffold(
        topBar = {
            // TopAppBar with custom colors and title
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 20.dp), // Ensures title is centered with back button
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = stringResource(id = R.string.title_movie_list))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE),
                    titleContentColor = Color.White
                )
            )
        },
        content = { padding ->
            // Handling different states of the UI: Loading, Success, and Error
            when (val state = uiState.value) {
                is UiState.Loading -> {
                    // Display a loading indicator in the center of the screen
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is UiState.Success -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp)
                        ) {
                            items(state.data) { movie ->
                                val isFavorite = favoriteMovies.value.contains(movie.id)
                                MovieCard(
                                    name = movie.name ?: stringResource(id = R.string.not_available),
                                    runtimeInMinutes = movie.runtimeInMinutes ?: 0,
                                    budgetInMillions = movie.budgetInMillions ?: 0.0,
                                    boxOfficeRevenueInMillions = movie.boxOfficeRevenueInMillions ?: 0.0,
                                    academyAwardNominations = movie.academyAwardNominations ?: 0,
                                    academyAwardWins = movie.academyAwardWins ?: 0,
                                    isFavorite = isFavorite,
                                    onFavoriteClick = {
                                        toggleFavorite(sharedPreferences, movie.id.toString()) { updatedFavorites ->
                                            favoriteMovies.value = updatedFavorites // Update the favorites state
                                        }
                                    }
                                ) {
                                    // Handle click event to navigate to the detail screen
                                    val movieJson = Uri.encode(Gson().toJson(movie))
                                    navController.navigate("${AppConstants.ROUTE_CHARACTERS_SCREEN}/${movieJson}")
                                }
                            }
                        }
                    }
                }
                is UiState.Error -> {
                    // Display an error message in the center of the screen
                    ErrorText(message = state.message)
                }
                else -> TODO()
            }
        }
    )
}