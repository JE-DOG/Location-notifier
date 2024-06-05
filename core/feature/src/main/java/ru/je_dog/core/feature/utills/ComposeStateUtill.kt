package ru.je_dog.core.feature.utills

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.StateFlow

@Composable
fun<T> CollectStateFlow(
    stateFlow: StateFlow<T>,
    onCollect: (T) -> Unit
) {
    LaunchedEffect(stateFlow){
        stateFlow.collect(onCollect)
    }
}