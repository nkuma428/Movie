package com.app.movie.presentation.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.app.movie.R
import com.app.movie.data.model.Character
import com.app.movie.data.model.Quote

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    navController: NavHostController,
    character: Character,
    quoteList: List<Quote>
) {

    Scaffold(
        topBar = {
            // TopAppBar for the screen
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = stringResource(id = R.string.title_character_detail))
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE),
                    titleContentColor = Color.White
                )
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
                        spouse = if (character.spouse == true) "Yes" else "No",
                        quoteList = quoteList
                    )
                }
            }
        }
    )
}
