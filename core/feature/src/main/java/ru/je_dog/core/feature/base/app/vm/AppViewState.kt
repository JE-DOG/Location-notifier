package ru.je_dog.core.feature.base.app.vm

import ru.je_dog.core.feature.base.app.tool_bar.AppToolBar
import ru.je_dog.core.feature.base.vm.ViewState

data class AppViewState(
    val toolBar: AppToolBar = AppToolBar(""),
): ViewState