package com.app.movie.presentation.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.app.movie.R

@Composable
fun ErrorText(message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp), // Fill the entire area of the parent
        contentAlignment = Alignment.Center // Center the content inside the Box
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold), // Bold text
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center, // Center the text
            modifier = Modifier.wrapContentWidth()
        )
    }
}