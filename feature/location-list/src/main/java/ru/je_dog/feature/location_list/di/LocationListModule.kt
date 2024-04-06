package ru.je_dog.feature.location_list.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ru.je_dog.data.location_list.di.LocationRepositoryModule
import ru.je_dog.feature.location_list.vm.LocationListViewModel

@Module(
    includes = [
        LocationRepositoryModule::class
    ]
)
internal class LocationListModule {

    @Provides
    fun provideViewModelFactory(
        viewModel: LocationListViewModel
    ): ViewModelProvider.Factory {
        val factory = object: ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return viewModel as T
            }
        }
        return factory
    }

}