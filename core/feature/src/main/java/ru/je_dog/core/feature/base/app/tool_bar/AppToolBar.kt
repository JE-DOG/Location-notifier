package ru.je_dog.core.feature.base.app.tool_bar

data class AppToolBar(
    val title: String,
    val starItem: AppToolBarItem? = null,
    val endItems: List<AppToolBarItem>? = null
)