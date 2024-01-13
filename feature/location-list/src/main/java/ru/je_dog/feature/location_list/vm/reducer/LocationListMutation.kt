package ru.je_dog.feature.location_list.vm.reducer

import ru.je_dog.core.feature.base.vm.reducer.Mutation
import ru.je_dog.core.feature.model.GeoPointPresentation

internal sealed interface LocationListMutation: Mutation {

    object ShowError: LocationListMutation

    object ShowLoading: LocationListMutation

    data class ShowLocations(
        val locations: List<GeoPointPresentation>
    ): LocationListMutation

}