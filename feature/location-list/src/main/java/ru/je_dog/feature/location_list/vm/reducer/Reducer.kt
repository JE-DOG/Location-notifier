package ru.je_dog.feature.location_list.vm.reducer

import ru.je_dog.feature.location_list.vm.LocationListViewState

internal class Reducer {

    fun invoke(mutation: Mutation, currentState: LocationListViewState): LocationListViewState = when(mutation) {

        is Mutation.ShowError -> currentState.copy(
            isLoading = false,
            isError = true
        )

        is Mutation.ShowLoading -> currentState.copy(
            isLoading = true,
            isError = false
        )

        is Mutation.ShowLocations -> currentState.copy(
            isLoading = false,
            isError = false,
            locations = mutation.locations
        )

    }

}