package ru.je_dog.feature.location_list.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import ru.je_dog.core.feature.base.app.vm.AppViewModel
import ru.je_dog.feature.location_list.di.deps.LocationListComponentDeps
import ru.je_dog.feature.location_list.vm.LocationListViewModel

@Component(
    modules = [
        LocationListModule::class
    ],
    dependencies = [
        LocationListComponentDeps::class
    ]
)
internal interface LocationListComponent {

    val locationListViewModel: LocationListViewModel

    @Component.Factory
    interface Factory {

        fun create(
            deps: LocationListComponentDeps
        ): LocationListComponent

    }

}