package ru.je_dog.core.feature.base.app.tool_bar

import androidx.annotation.DrawableRes
import ru.je_dog.core.feature.R

data class AppToolBarItem(
    @DrawableRes
    val icon: Int,
    val action: (() -> Unit)
){
    companion object {

        fun back(action: () -> Unit) = AppToolBarItem(
            icon = R.drawable.ic_back_arrow,
            action = action
        )

        fun more(action: () -> Unit) = AppToolBarItem(
            icon = R.drawable.ic_more_vertical,
            action = action
        )

    }
}

