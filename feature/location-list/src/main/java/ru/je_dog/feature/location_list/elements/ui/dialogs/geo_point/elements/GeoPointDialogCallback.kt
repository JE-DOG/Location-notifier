package ru.je_dog.feature.location_list.elements.ui.dialogs.geo_point.elements

import ru.je_dog.core.feature.model.GeoPointPresentation

internal interface GeoPointDialogCallback {
    fun hideGeoPointDialog()
    fun onConfirmGeoPoint(geoPoint: GeoPointPresentation)
    fun getLocation(geoPointDialogState: GeoPointDialogState)

    object Empty: GeoPointDialogCallback {
        override fun hideGeoPointDialog() {
            TODO("Not yet implemented")
        }
        override fun getLocation(geoPointDialogState: GeoPointDialogState) {
            TODO("Not yet implemented")
        }
        override fun onConfirmGeoPoint(geoPoint: GeoPointPresentation) {
            TODO("Not yet implemented")
        }
    }
}