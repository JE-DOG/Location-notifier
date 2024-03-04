package ru.je_dog.core.feature.app.vm

import ru.je_dog.core.feature.app.tool_bar.ToolBar
import ru.je_dog.core.feature.common.vm.ViewState

data class MainViewState(
    val isOnline: Boolean = true
): ViewState
