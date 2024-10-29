package com.app.movie.presentation.characters

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.movie.R
import com.app.movie.data.model.Character
import com.app.movie.presentation.characters.viewmodel.CharacterViewModel
import com.app.movie.util.AppConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(navController: NavHostController, character: Character, viewModel: CharacterViewModel = hiltViewModel()) {

    Scaffold(
        topBar = {
            // TopAppBar for the screen
            TopAppBar(
                title = { Text(stringResource(id = R.string.title_character_detail)) },
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)) {
                    Text(
                        text = "Name: ${character.name}",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = "Race: ${character.race}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Gender: ${character.gender}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Spouse: ${character.spouse}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 16.sp
                    )

                    ClickableText(
                        text = AnnotatedString("Wiki Page"),
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Blue),
                        onClick = {
                            navController.navigate("${AppConstants.ROUTE_QUOTE_LIST_SCREEN}/${character.id}")
                        }
                    )
                }
            }
        }
    )
}
