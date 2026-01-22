package com.fortunateworld.grokxxx.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HistoryScreen() {
    Text(
        "History & Favorites\n\nYour generated images and video prompts will appear here soon.",
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(24.dp)
    )
}
