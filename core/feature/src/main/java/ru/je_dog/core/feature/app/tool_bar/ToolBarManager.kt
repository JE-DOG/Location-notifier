package ru.je_dog.core.feature.app.tool_bar

import kotlinx.coroutines.flow.StateFlow

interface ToolBarManager {

    val toolBar: StateFlow<ToolBar?>

    fun updateToolBar(toolBar: ToolBar?)

}