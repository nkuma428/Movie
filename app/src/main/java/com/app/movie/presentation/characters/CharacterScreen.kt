package com.app.movie.presentation.characters

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.app.movie.R
import com.app.movie.data.model.Movie
import com.app.movie.presentation.widgets.CenteredTopAppBar
import com.app.movie.util.AppConstants
import com.google.gson.Gson

/**
 * Composable function to display the characters screen for a given movie.
 *
 * @param navController The navigation controller to handle navigation actions.
 * @param movie The movie data containing the list of characters.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(navController: NavHostController, movie: Movie) {

    Scaffold(
        topBar = {
            // TopAppBar for the screen
            CenteredTopAppBar(
                title = stringResource(R.string.title_character_list),
                onBackClick = {
                    navController.popBackStack()
                }
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                if (movie.characterList.isEmpty()) {
                    // Display "Record not found" message in the center
                    Text(
                        text = stringResource(R.string.record_not_found_please_check_with_diff_movie),
                        color = Color.Gray,
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {

                        items(movie.characterList) { character ->
                            CharacterListItem(character) {
                                // Handle click event to navigate to the detail screen
                                val characterJson = Uri.encode(Gson().toJson(character))
                                val quoteListJson = Uri.encode(Gson().toJson(movie.quoteList))
                                navController.navigate("${AppConstants.ROUTE_CHARACTERS_DETAILS_SCREEN}/${characterJson}/${quoteListJson}")
                            }
                        }
                    }
                }
            }
        }
    )
}
