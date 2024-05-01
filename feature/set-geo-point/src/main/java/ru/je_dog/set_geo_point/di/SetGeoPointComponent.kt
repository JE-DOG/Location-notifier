package ru.je_dog.set_geo_point.di

import dagger.Component
import ru.je_dog.set_geo_point.di.deps.SetGeoPointComponentDeps
import ru.je_dog.set_geo_point.vm.SetGeoPointViewModel
import javax.inject.Scope

@Component(
    dependencies = [
        SetGeoPointComponentDeps::class
    ]
)
interface SetGeoPointComponent {

    val viewModel: SetGeoPointViewModel

    @Component.Factory
    interface Factory {
        fun create(
            deps: SetGeoPointComponentDeps
        ): SetGeoPointComponent
    }
}