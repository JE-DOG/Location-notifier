package ru.je_dog.feature.location_list.vm.reducer

import ru.je_dog.core.feature.base.vm.reducer.Reducer
import ru.je_dog.feature.location_list.elements.state.LocationListViewState

internal class LocationListReducer: Reducer<LocationListViewState,LocationListMutation> {

    override fun invoke(mutation: LocationListMutation, currentState: LocationListViewState): LocationListViewState = when(mutation) {
        is LocationListMutation.ShowError -> currentState.copy(
            isLoading = false,
            isError = true
        )

        is LocationListMutation.ShowLoading -> currentState.copy(
            isLoading = true,
            isError = false
        )

        is LocationListMutation.ShowLocations -> currentState.copy(
            isLoading = false,
            isError = false,
            locations = mutation.locations
        )

        is LocationListMutation.ShowGeoPointDialog -> currentState.copy(
            geoPointCreateDialog = mutation.geoPointDialogState
        )

        is LocationListMutation.HideGeoPointDialog -> currentState.copy(
            geoPointCreateDialog = null
        )

        is LocationListMutation.SetLocationForGeoPointDialog -> currentState.copy(
            geoPointCreateDialog = currentState.geoPointCreateDialog?.apply {
                geoPoint = geoPoint?.copy(
                    longitude = mutation.location.longitude,
                    latitude = mutation.location.latitude,
                )
            }
        )
    }
}