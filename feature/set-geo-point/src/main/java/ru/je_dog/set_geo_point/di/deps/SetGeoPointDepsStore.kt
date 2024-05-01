package ru.je_dog.set_geo_point.di.deps

import ru.je_dog.core.feature.base.di.DaggerDepsProvider
import kotlin.properties.Delegates

object SetGeoPointDepsStore: DaggerDepsProvider<SetGeoPointComponentDeps> {
    override var deps: SetGeoPointComponentDeps by Delegates.notNull()
}