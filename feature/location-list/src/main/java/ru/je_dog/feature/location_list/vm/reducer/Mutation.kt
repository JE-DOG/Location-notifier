package ru.je_dog.feature.location_list.vm.reducer

import ru.je_dog.core.feature.model.GeoPointPresentation

internal sealed interface Mutation {

    object ShowError: Mutation

    object ShowLoading: Mutation

    data class ShowLocations(
        val locations: List<GeoPointPresentation>
    ): Mutation

}