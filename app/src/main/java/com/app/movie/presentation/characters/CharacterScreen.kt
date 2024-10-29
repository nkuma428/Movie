package com.app.movie.presentation.characters

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.movie.R
import com.app.movie.presentation.characters.viewmodel.CharacterViewModel
import com.app.movie.util.AppConstants
import com.app.movie.util.UiState
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(navController: NavHostController, movieId: String, viewModel: CharacterViewModel = hiltViewModel()) {

    LaunchedEffect(movieId) {
        viewModel.getCharactersByMovieId(movieId)
    }

    val uiState = viewModel.characterResponse.collectAsState()

    Scaffold(
        topBar = {
            // TopAppBar for the screen
            TopAppBar(
                title = { Text(stringResource(id = R.string.title_character_list)) },
                actions = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            painter = painterResource(id = androidx.core.R.drawable.ic_call_answer),
                            contentDescription = "Refresh",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE), // Custom background color
                    titleContentColor = Color.White // Custom title color
                )
            )
        },
        content = { padding ->

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

                            items(state.data) { character ->
                                CharacterListItem(character) {
                                    // Handle click event to navigate to the detail screen
                                    val characterJson = Uri.encode(Gson().toJson(character))
                                    navController.navigate("${AppConstants.ROUTE_CHARACTERS_DETAILS_SCREEN}/${characterJson}")
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
