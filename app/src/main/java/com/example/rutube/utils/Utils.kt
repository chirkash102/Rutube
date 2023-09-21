package com.example.rutube.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun <T> SharedFlow<T>.collectAsEvent(block: (T) -> Unit) = LaunchedEffect(Unit) { collect(block::invoke) }
