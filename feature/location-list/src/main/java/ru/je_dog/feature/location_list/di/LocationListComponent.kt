package ru.je_dog.feature.location_list.di

import dagger.Component
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