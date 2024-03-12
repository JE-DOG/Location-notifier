package ru.je_dog.location_notifier.vm

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.je_dog.core.feature.base.app.tool_bar.AppToolBar
import ru.je_dog.core.feature.base.app.vm.AppViewModel
import ru.je_dog.core.feature.base.app.vm.AppViewState
import ru.je_dog.core.feature.base.app.vm.reducer.AppViewModelReducer
import ru.je_dog.location_notifier.vm.reducer.AppMutationImpl
import ru.je_dog.location_notifier.vm.reducer.AppViewModelReducerImpl

class AppViewModelImpl(
    appViewModelReducer: AppViewModelReducer
): AppViewModel(appViewModelReducer) {

    private val _state = MutableStateFlow(AppViewState())
    override val state: StateFlow<AppViewState> = _state

    override fun changeToolbar(appToolBar: AppToolBar) {
        val mutation = AppMutationImpl.ShowToolBar(appToolBar)
        mutation.reduce(_state)
    }

}