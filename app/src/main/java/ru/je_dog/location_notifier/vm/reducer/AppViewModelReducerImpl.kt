package ru.je_dog.location_notifier.vm.reducer

import ru.je_dog.core.feature.base.app.vm.AppViewState
import ru.je_dog.core.feature.base.app.vm.reducer.AppMutation
import ru.je_dog.core.feature.base.app.vm.reducer.AppViewModelReducer

class AppViewModelReducerImpl: AppViewModelReducer {

    override fun invoke(mutation: AppMutation, currentViewState: AppViewState): AppViewState = when(mutation) {

        is AppMutationImpl.ShowToolBar -> {
            currentViewState.copy(
                toolBar = mutation.appToolBar
            )
        }

        else -> {
            error("Unknown mutation. Error place - ${this::class.simpleName}")
        }
    }
}