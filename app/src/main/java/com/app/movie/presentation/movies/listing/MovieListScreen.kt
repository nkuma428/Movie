package com.app.movie.presentation.movies.listing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.movie.R
import com.app.movie.presentation.movies.viewmodel.MovieListViewModel
import com.app.movie.util.AppConstants
import com.app.movie.util.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(viewModel: MovieListViewModel, navController: NavController) {

    val uiState = viewModel.movieResponse.collectAsState()

    Scaffold(
        topBar = {
            // TopAppBar with custom colors and title
            TopAppBar(
                title = { Text(stringResource(id = R.string.title_movie_list)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE), // Custom background color
                    titleContentColor = Color.White // Custom title color
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
                                MovieListItem(movie) {
                                    // Handle click event to navigate to the detail screen
                                    navController.navigate("${AppConstants.ROUTE_CHARACTERS_SCREEN}/${movie.id.toString()}")
                                }
                            }
                        }
                    }
                }
                is UiState.Error -> {
                    // Display an error message in the center of the screen
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = state.message)
                    }
                }
                else -> TODO()
            }
        }
    )
}