package ru.je_dog.set_geo_point.vm.components

import ru.je_dog.core.feature.base.vm.reducer.Reducer
import ru.je_dog.set_geo_point.vm.SetGeoPointViewState

class SetGeoPointReducer: Reducer<SetGeoPointViewState, SetGeoPointMutation> {

    override fun invoke(
        mutation: SetGeoPointMutation,
        currentViewState: SetGeoPointViewState
    ): SetGeoPointViewState {
        return when(mutation){

            is SetGeoPointMutation.SetUserGeoPoint -> {
                currentViewState.copy(
                    userLocation = mutation.userGeoPoint
                )
            }

            is SetGeoPointMutation.SetGoalLocation -> {
                currentViewState.copy(
                    selectedLocation = mutation.location
                )
            }

            is SetGeoPointMutation.SetCanLocationState -> {
                currentViewState.copy(
                    canGetLocation = mutation.canGetLocation
                )
            }

        }
    }
}