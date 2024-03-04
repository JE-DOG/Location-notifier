package ru.je_dog.core.feature.app.vm

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.je_dog.core.feature.app.tool_bar.ToolBarManager
import ru.je_dog.core.feature.common.vm.BaseViewModel

abstract class MainViewModel: BaseViewModel<MainViewState, MainAction>(
    initialState = MainViewState()
), ToolBarManager {

    override fun action(action: MainAction) {
        TODO("Not yet implemented")
    }

}