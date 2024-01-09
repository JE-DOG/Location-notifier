package ru.je_dog.feature.location_list.vm

import ru.je_dog.core.feature.model.GeoPointPresentation

internal sealed interface Action {

    data class AddLocation(
        val geoPointPresentation: GeoPointPresentation
    ): Action

    data class DeleteLocation(
        val geoPointPresentation: GeoPointPresentation
    ): Action

    data class UpdateLocation(
        val geoPointPresentation: GeoPointPresentation
    ): Action

    object GetAllLocation: Action

    object DeleteAllLocation: Action

}