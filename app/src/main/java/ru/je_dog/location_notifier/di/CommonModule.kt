package ru.je_dog.location_notifier.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.je_dog.core.feature.base.app.vm.AppViewModel
import ru.je_dog.core.feature.base.app.vm.reducer.AppViewModelReducer
import ru.je_dog.core.feature.base.service.location.LocationManager
import ru.je_dog.core.feature.base.service.location.LocationManagerImpl
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

    @Provides
    fun provideLocationManager(
        context: Context
    ): LocationManager {
        return LocationManagerImpl(context)
    }

}