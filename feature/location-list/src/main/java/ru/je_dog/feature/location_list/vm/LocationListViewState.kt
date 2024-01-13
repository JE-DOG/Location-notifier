package ru.je_dog.feature.location_list.vm

import ru.je_dog.core.feature.base.vm.ViewState
import ru.je_dog.core.feature.model.GeoPointPresentation

data class LocationListViewState(
    val locations: List<GeoPointPresentation> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
): ViewState