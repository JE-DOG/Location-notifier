package ru.je_dog.feature.location_list.elements.state

import ru.je_dog.core.feature.base.vm.ViewState
import ru.je_dog.core.feature.model.GeoPointPresentation
import ru.je_dog.feature.location_list.elements.ui.dialogs.geo_point.elements.GeoPointDialogStateSaver
import ru.je_dog.feature.location_list.elements.ui.dialogs.geo_point.elements.GeoPointDialogState

data class LocationListViewState(
    val locations: List<GeoPointPresentation> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val geoPointCreateDialog: GeoPointDialogState? = GeoPointDialogStateSaver.dialogState,
): ViewState