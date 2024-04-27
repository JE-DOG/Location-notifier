package ru.je_dog.location_notifier.di

import dagger.Module
import dagger.Provides
import ru.je_dog.core.feature.base.app.vm.AppViewModel
import ru.je_dog.core.feature.base.app.vm.reducer.AppViewModelReducer
import ru.je_dog.location_notifier.vm.AppViewModelImpl
import ru.je_dog.location_notifier.vm.reducer.AppViewModelReducerImpl

@Module
class CommonModule {

    @Provides
    fun provideAppViewModelReducer(): AppViewModelReducer {
        return AppViewModelReducerImpl()
    }

    @Provides
    fun provideAppViewModel(
        appViewModelReducer: AppViewModelReducer
    ): AppViewModel {
        return AppViewModelImpl(
            appViewModelReducer
        )
    }

}