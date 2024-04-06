package ru.je_dog.core.feature.base.vm.reducer

import ru.je_dog.core.feature.base.vm.ViewState

interface Reducer<VS:ViewState,M:Mutation> {

    fun invoke(mutation: M, currentViewState: VS): VS

}