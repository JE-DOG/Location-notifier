package ru.je_dog.core.feature.composition

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.compose.runtime.compositionLocalOf

val LocalAppToolBarTitle = compositionLocalOf<MutableState<String>> {
    throw IllegalArgumentException("Not init")
}