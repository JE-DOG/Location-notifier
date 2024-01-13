package ru.je_dog.core.feature.base.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.je_dog.core.feature.base.vm.reducer.Mutation
import ru.je_dog.core.feature.base.vm.reducer.Reducer

abstract class BaseViewModel<VS: ViewState,M: Mutation,R: Reducer<VS,M>>(
    protected val _state: MutableStateFlow<VS>
): ViewModel() {

    protected abstract val reducer: R
    val state: StateFlow<VS> = _state

    protected fun M.reduce(){
        _state.value = reducer.invoke(this@reduce,state.value)
    }

}