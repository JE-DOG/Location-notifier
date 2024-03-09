package ru.je_dog.core.feature.composition

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf
import ru.je_dog.core.feature.base.app.tool_bar.AppToolBar

val LocalAppToolBarTitle = compositionLocalOf<MutableState<AppToolBar>> {
    throw IllegalArgumentException("Not init")
}