package ru.je_dog.feature.location_list.di.deps

import ru.je_dog.core.feature.base.di.DaggerDepsProvider
import kotlin.properties.Delegates

object LocationListComponentDepsStore: DaggerDepsProvider<LocationListComponentDeps> {

    override var deps: LocationListComponentDeps by Delegates.notNull()

}