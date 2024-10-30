package com.app.movie.presentation.movies.listing

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight


/**
 * Composable function to display a movie card with various details.
 *
 * @param name The name of the movie.
 * @param runtimeInMinutes The runtime of the movie in minutes.
 * @param budgetInMillions The budget of the movie in millions.
 * @param boxOfficeRevenueInMillions The box office revenue of the movie in millions.
 * @param academyAwardNominations The number of Academy Award nominations the movie received.
 * @param academyAwardWins The number of Academy Awards the movie won.
 * @param onClick The callback to be invoked when the card is clicked.
 */
@Composable
fun MovieCard(
    name: String,
    runtimeInMinutes: Int,
    budgetInMillions: Double,
    boxOfficeRevenueInMillions: Double,
    academyAwardNominations: Int,
    academyAwardWins: Int,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(12.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold), // Bold text
                    color = MaterialTheme.colorScheme.onSurface,
                )

                // Favorite icon
                IconButton(onClick = onFavoriteClick) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = if (isFavorite) "Unfavorite" else "Favorite",
                        tint = if (isFavorite) Color.Red else Color.Gray // Change color based on favorite status
                    )
                }
            }

            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
            )

            Column(modifier = Modifier.padding(top = 8.dp)) {
                MovieInfoRow(label = "Runtime", value = "$runtimeInMinutes min", icon = Icons.Default.AccessTime)
                MovieInfoRow(label = "Budget", value = "$${budgetInMillions}M", icon = Icons.Default.AttachMoney)
                MovieInfoRow(label = "Box Office", value = "$${boxOfficeRevenueInMillions}M", icon = Icons.Default.TrendingUp)
                MovieInfoRow(label = "Nominations", value = academyAwardNominations.toString(), icon = Icons.Default.ThumbUp)
                MovieInfoRow(label = "Awards Won", value = academyAwardWins.toString(), icon = Icons.Default.Star)
            }
        }
    }
}