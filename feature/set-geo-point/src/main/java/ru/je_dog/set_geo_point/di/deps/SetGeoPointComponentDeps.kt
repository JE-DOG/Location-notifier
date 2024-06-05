package ru.je_dog.set_geo_point.di.deps

import ru.je_dog.core.feature.base.di.DaggerComponentDeps
import ru.je_dog.core.feature.base.service.location.LocationManager

interface SetGeoPointComponentDeps: DaggerComponentDeps {

    val locationManager: LocationManager

}