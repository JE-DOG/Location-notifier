package ru.je_dog.feature.location_list.vm

import ru.je_dog.core.feature.base.vm.Action
import ru.je_dog.core.feature.model.BaseLocation
import ru.je_dog.core.feature.model.GeoPointPresentation
import ru.je_dog.feature.location_list.elements.ui.dialogs.geo_point.elements.GeoPointDialogState

internal sealed interface LocationListAction: Action {

    data class AddLocation(
        val geoPoint: GeoPointPresentation
    ): LocationListAction

    data class DeleteLocation(
        val geoPoint: GeoPointPresentation
    ): LocationListAction

    data class UpdateLocation(
        val geoPoint: GeoPointPresentation
    ): LocationListAction

    object GetAllLocation: LocationListAction

    object DeleteAllLocation: LocationListAction

    object HideGeoPointDialog: LocationListAction

    data class ShowGeoPointDialog(
        val geoPointDialogState: GeoPointDialogState
    ): LocationListAction

    data class SetLocationForGeoPointDialog(
        val location: BaseLocation,
    ): LocationListAction
}