package ru.je_dog.set_geo_point.vm

import org.osmdroid.util.GeoPoint
import ru.je_dog.core.feature.base.vm.ViewState
import ru.je_dog.core.feature.model.BaseLocation

data class SetGeoPointViewState(
    val canGetLocation: Boolean = false,
    val userLocation: GeoPoint? = null,
    val selectedLocation: BaseLocation? = null,
): ViewState
