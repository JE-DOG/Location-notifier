package ru.je_dog.core.feature.app.tool_bar

import androidx.annotation.StringRes

sealed interface ToolBarTitle {

    data class Text(
        @StringRes
        val text: Int
    ): ToolBarTitle

}