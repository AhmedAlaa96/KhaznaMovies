package com.example.khaznamovies.ui.movieslist.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ErrorScreen(error: String, modifier: Modifier = Modifier) {
    Text(
        text = error,
        modifier = modifier,
        color = Color.Black,
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center
    )
}