package ru.je_dog.feature.location_list.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import ru.je_dog.feature.location_list.di.deps.LocationListComponentDeps

@Component(
    modules = [
        LocationListModule::class
    ],
    dependencies = [
        LocationListComponentDeps::class
    ]
)
internal interface LocationListComponent {

    val viewModelFactory: ViewModelProvider.Factory

    @Component.Factory
    interface Factory {

        fun create(
            deps: LocationListComponentDeps
        ): LocationListComponent

    }

}