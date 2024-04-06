package ru.je_dog.feature.location_list.vm.reducer

import ru.je_dog.core.feature.base.vm.reducer.Mutation
import ru.je_dog.core.feature.model.BaseLocation
import ru.je_dog.core.feature.model.GeoPointPresentation
import ru.je_dog.feature.location_list.elements.ui.dialogs.geo_point.elements.GeoPointDialogState

internal sealed interface LocationListMutation: Mutation {

    object ShowError: LocationListMutation

    object ShowLoading: LocationListMutation

    data class ShowLocations(
        val locations: List<GeoPointPresentation>
    ): LocationListMutation

    data class ShowGeoPointDialog(
        val geoPointDialogState: GeoPointDialogState
    ): LocationListMutation

    data class SetLocationForGeoPointDialog(
        val location: BaseLocation
    ): LocationListMutation

    object HideGeoPointDialog: LocationListMutation
}