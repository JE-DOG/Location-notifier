package ru.je_dog.set_geo_point.vm.components

import ru.je_dog.core.feature.base.vm.Action
import ru.je_dog.core.feature.model.BaseLocation

sealed interface SetGeoPointAction: Action {
    sealed interface UI {

        object ShowUserLocation: UI

        data class ChangeGoalLocation(
            val location: BaseLocation
        ): UI

        object OnConfirm: UI

    }

    sealed interface Internal {

        data class GpsStateChange(
            val gpsState: Boolean
        ): Internal

    }
}