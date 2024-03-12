package ru.je_dog.core.feature.base.app.vm

import kotlinx.coroutines.flow.StateFlow
import ru.je_dog.core.feature.base.app.tool_bar.AppToolBar
import ru.je_dog.core.feature.base.app.vm.reducer.AppMutation
import ru.je_dog.core.feature.base.app.vm.reducer.AppViewModelReducer
import ru.je_dog.core.feature.base.vm.BaseViewModel

abstract class AppViewModel(
    reducer: AppViewModelReducer
): BaseViewModel<AppViewState,AppMutation,AppViewModelReducer>(reducer) {

    abstract val state: StateFlow<AppViewState>

    abstract fun changeToolbar(appToolBar: AppToolBar)

}