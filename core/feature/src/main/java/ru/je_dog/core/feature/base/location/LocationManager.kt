package ru.je_dog.core.feature.base.location

import kotlinx.coroutines.flow.Flow
import org.osmdroid.util.GeoPoint
import ru.je_dog.core.feature.model.GeoPointPresentation

interface LocationManager {

    fun getLocation(): GeoPointPresentation?

    fun broadcastLocation(secondsInterval: Long): Flow<GeoPointPresentation>

}