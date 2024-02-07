package ru.je_dog.core.feature.navigation

import androidx.annotation.StringRes

data class AppToolBar(
    val title: String,
    val starItem: AppToolBarItem? = null,
    val endItems: List<AppToolBarItem>? = null
)