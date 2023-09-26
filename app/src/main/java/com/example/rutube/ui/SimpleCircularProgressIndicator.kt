package com.example.rutube.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SimpleCircularProgressIndicator(
    isVisible: Boolean,
    modifier: Modifier = Modifier
        .fillMaxSize()
        .clickable(enabled = false, onClick = { Unit })
        .background(MaterialTheme.colorScheme.background.copy(alpha = 0.6f)),
) {
    if (isVisible) {
        Box(modifier = modifier) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .height(35.dp)
                    .width(35.dp)
                    .align(Alignment.Center)
            )
        }
    }
}
