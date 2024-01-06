package ru.je_dog.core.feature.location

import kotlinx.coroutines.flow.Flow
import org.osmdroid.util.GeoPoint

interface LocationManager {

    fun getLocation(): GeoPoint?

    fun broadcastLocation(secondsInterval: Long): Flow<GeoPoint>

    fun isLocationNearby(geoPoint: GeoPoint, meters: Int): Boolean

}