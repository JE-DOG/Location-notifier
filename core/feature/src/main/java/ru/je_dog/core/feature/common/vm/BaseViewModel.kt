package ru.je_dog.core.feature.common.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.je_dog.core.feature.common.vm.reducer.Mutation
import ru.je_dog.core.feature.common.vm.reducer.Reducer

abstract class BaseViewModel<VS: ViewState,A: Action>(
    initialState: VS
): ViewModel() {

    protected val _state = MutableStateFlow(initialState)
    val state: StateFlow<VS> = _state

    abstract fun action(action: A)

}