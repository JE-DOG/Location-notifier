package ru.je_dog.feature.location_list.elements.callback

import ru.je_dog.core.feature.model.GeoPointPresentation
import ru.je_dog.feature.location_list.elements.ui.dialogs.geo_point.elements.GeoPointDialogState

object EmptyLocationListCallback: LocationListCallback {
    override fun updateGeoPoint(geoPoint: GeoPointPresentation) {
        TODO("Not yet implemented")
    }

    override fun hideGeoPointDialog() {
        TODO("Not yet implemented")
    }

    override fun onConfirmGeoPoint(geoPoint: GeoPointPresentation) {
        TODO("Not yet implemented")
    }

    override fun getLocation(geoPointDialogState: GeoPointDialogState) {
        TODO("Not yet implemented")
    }

    override fun deleteGeoPoint(geoPoint: GeoPointPresentation) {
        TODO("Not yet implemented")
    }

    override fun onItemClick(geoPoint: GeoPointPresentation) {
        TODO("Not yet implemented")
    }

    override fun onAddGeoPointClick() {
        TODO("Not yet implemented")
    }

    override fun onTryAgainClick() {
        TODO("Not yet implemented")
    }
}