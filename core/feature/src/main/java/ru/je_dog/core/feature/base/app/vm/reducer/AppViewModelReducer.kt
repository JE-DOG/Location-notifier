package ru.je_dog.core.feature.base.app.vm.reducer

import ru.je_dog.core.feature.base.app.vm.AppViewState
import ru.je_dog.core.feature.base.vm.reducer.Reducer

interface AppViewModelReducer: Reducer<AppViewState,AppMutation>