package ru.je_dog.set_geo_point.vm.components

import org.osmdroid.util.GeoPoint
import ru.je_dog.core.feature.base.vm.reducer.Mutation
import ru.je_dog.core.feature.model.BaseLocation

sealed interface SetGeoPointMutation: Mutation {

    data class SetUserGeoPoint(
        val userGeoPoint: GeoPoint?
    ): SetGeoPointMutation

    data class SetGoalLocation(
        val location: BaseLocation
    ): SetGeoPointMutation

    data class SetCanLocationState(
        val canGetLocation: Boolean
    ): SetGeoPointMutation

}