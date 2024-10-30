package com.app.movie.presentation.characters

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.app.movie.R
import com.app.movie.data.model.Character
import com.app.movie.data.model.Quote
import com.app.movie.presentation.widgets.CenteredTopAppBar

/**
 * Composable function to display the character detail screen.
 *
 * @param navController The navigation controller to handle navigation actions.
 * @param character The character data to be displayed.
 * @param quoteList The list of quotes associated with the character.
 */
@Composable
fun CharacterDetailScreen(
    navController: NavHostController,
    character: Character,
    quoteList: List<Quote>
) {
    Scaffold(
        topBar = {
            // TopAppBar for the screen
            CenteredTopAppBar(
                title = stringResource(R.string.title_character_detail),
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
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)) {

                    CharacterDetail(
                        navController = navController,
                        name = character.name?: stringResource(id = R.string.not_available),
                        race = character.race?: stringResource(id = R.string.not_available),
                        gender = character.gender?: stringResource(id = R.string.not_available),
                        birth = character.birth?: stringResource(id = R.string.not_available),
                        spouse = if (character.spouse == true) "Yes" else "No",
                        quoteList = quoteList
                    )
                }
            }
        }
    )
}
