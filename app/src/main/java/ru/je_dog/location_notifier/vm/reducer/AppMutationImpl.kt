package ru.je_dog.location_notifier.vm.reducer

import ru.je_dog.core.feature.base.app.tool_bar.AppToolBar
import ru.je_dog.core.feature.base.app.vm.reducer.AppMutation

sealed interface AppMutationImpl: AppMutation {

    data class ShowToolBar(
        val appToolBar: AppToolBar
    ): AppMutation

}