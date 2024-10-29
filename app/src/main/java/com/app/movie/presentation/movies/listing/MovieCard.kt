package com.app.movie.presentation.movies.listing

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MovieCard(
    name: String,
    runtimeInMinutes: Int,
    budgetInMillions: Double,
    boxOfficeRevenueInMillions: Double,
    academyAwardNominations: Int,
    academyAwardWins: Int,
    onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },// Makes the card clickable
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Divider for separating title and details
            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
            )

            Column(modifier = Modifier.padding(top = 8.dp)) {
                MovieInfoRow(label = "Runtime", value = "$runtimeInMinutes min", icon = Icons.Default.Star)
                MovieInfoRow(label = "Budget", value = "$${budgetInMillions}M", icon = Icons.Default.Star)
                MovieInfoRow(label = "Box Office", value = "$${boxOfficeRevenueInMillions}M", icon = Icons.Default.Star)
                MovieInfoRow(label = "Nominations", value = academyAwardNominations.toString(), icon = Icons.Default.ThumbUp)
                MovieInfoRow(label = "Awards Won", value = academyAwardWins.toString(), icon = Icons.Default.Star)
            }
        }
    }
}