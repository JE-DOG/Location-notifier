package ru.je_dog.core.feature.base.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.je_dog.core.feature.base.vm.reducer.Mutation
import ru.je_dog.core.feature.base.vm.reducer.Reducer

abstract class BaseViewModel<VS: ViewState,M: Mutation,R: Reducer<VS,M>>(
    private val reducer: R
): ViewModel() {
    protected fun M.reduce(state: MutableStateFlow<VS>){
        state.update { reducer.invoke(this@reduce,state.value) }
    }
}