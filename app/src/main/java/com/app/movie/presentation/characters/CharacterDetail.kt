package com.app.movie.presentation.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.app.movie.R
import com.app.movie.util.AppConstants
import kotlin.random.Random

@Composable
fun CharacterDetail(
    navController: NavHostController,
    characterId: String,
    name: String,
    race: String,
    gender: String,
    spouse: String
) {
    val enableQuotes = remember { mutableStateOf(Random.nextBoolean()) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),// Makes the card clickable
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                if (enableQuotes.value) {
                    ClickableText(
                        text = AnnotatedString(stringResource(R.string.see_quotes)),
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Blue),
                        onClick = {
                            navController.navigate("${AppConstants.ROUTE_QUOTE_LIST_SCREEN}/${characterId}")
                        }
                    )
                }
            }

            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f), thickness = 1.dp)

            Column(modifier = Modifier.padding(top = 8.dp)) {
                CharacterInfo(label = "Race", value = "$race")
                CharacterInfo(label = "Gender", value = "$gender")
                CharacterInfo(label = "Spouse", value = "$spouse")
            }
        }
    }
}