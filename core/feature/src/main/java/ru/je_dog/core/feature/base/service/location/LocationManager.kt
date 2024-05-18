package ru.je_dog.core.feature.base.service.location

import kotlinx.coroutines.flow.Flow
import ru.je_dog.core.feature.model.BaseLocation
import ru.je_dog.core.feature.model.GeoPointPresentation

interface LocationManager {

    fun gpsStatusBroadcast(): Flow<Boolean>

    suspend fun getCurrentLocation(): BaseLocation?

    fun broadcastLocation(secondsInterval: Long): Flow<GeoPointPresentation>

}