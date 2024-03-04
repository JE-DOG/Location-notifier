package ru.je_dog.core.feature.app.vm

import androidx.compose.runtime.compositionLocalOf

val LocalMainViewModel = compositionLocalOf<MainViewModel> {
    throw Exception("Not init")
}