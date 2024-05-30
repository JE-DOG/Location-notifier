package ru.je_dog.feature.location_list.vm

import ru.je_dog.core.feature.model.GeoPointPresentation

sealed interface LocationListEffects {

    data class StartBroadcast(
        val geoPointPresentation: GeoPointPresentation,
        val vibrationState: Boolean,
        val locationUpdateSecondsInterval: Int,
    ): LocationListEffects

}