package ru.je_dog.core.feature.common.vm.reducer

import ru.je_dog.core.feature.common.vm.ViewState

interface Reducer<VS: ViewState,M: Mutation> {

    fun invoke(mutation: M, currentViewState: VS): VS

}