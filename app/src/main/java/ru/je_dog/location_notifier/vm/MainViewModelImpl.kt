package ru.je_dog.location_notifier.vm

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.je_dog.core.feature.app.tool_bar.ToolBar
import ru.je_dog.core.feature.app.vm.MainViewModel

class MainViewModelImpl: MainViewModel() {

    private val _toolBar: MutableStateFlow<ToolBar?> = MutableStateFlow(null)
    override val toolBar: StateFlow<ToolBar?> = _toolBar

    override fun updateToolBar(toolBar: ToolBar?) = _toolBar.update { toolBar }

}