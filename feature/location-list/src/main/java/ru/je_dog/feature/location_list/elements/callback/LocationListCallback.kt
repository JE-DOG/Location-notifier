package ru.je_dog.feature.location_list.elements.callback

import ru.je_dog.core.feature.model.GeoPointPresentation
import ru.je_dog.feature.location_list.elements.ui.dialogs.geo_point.elements.GeoPointDialogCallback

internal interface LocationListCallback: GeoPointDialogCallback {
    fun updateGeoPoint(geoPoint: GeoPointPresentation)
    fun deleteGeoPoint(geoPoint: GeoPointPresentation)
    fun onItemClick(geoPoint: GeoPointPresentation)
    fun onTryAgainClick()

    //Create geo point callbacks
    fun onAddGeoPointClick()
}