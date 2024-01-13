package ru.je_dog.feature.location_list.vm

import ru.je_dog.core.feature.base.vm.Action
import ru.je_dog.core.feature.model.GeoPointPresentation

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

}