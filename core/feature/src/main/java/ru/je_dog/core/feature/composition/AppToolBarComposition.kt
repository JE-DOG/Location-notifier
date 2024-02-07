package ru.je_dog.core.feature.composition

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.compose.runtime.compositionLocalOf
import ru.je_dog.core.feature.navigation.AppToolBar

val LocalAppToolBarTitle = compositionLocalOf<MutableState<AppToolBar>> {
    throw IllegalArgumentException("Not init")
}