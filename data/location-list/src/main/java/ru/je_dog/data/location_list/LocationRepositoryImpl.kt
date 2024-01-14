package ru.je_dog.data.location_list

import android.util.Log
import kotlinx.coroutines.flow.Flow
import ru.je_dog.core.domain.model.GeoPointDomain
import ru.je_dog.data.location_list.storage.LocationStorageDataSource
import ru.je_dog.domain.location_list.LocationRepository

class LocationRepositoryImpl(
    private val locationStorageDataSource: LocationStorageDataSource
): LocationRepository {

    override fun getAllLocation(): Flow<List<GeoPointDomain>> {
        Log.d("RoomDataBase","Hello from repository")

        return locationStorageDataSource.getAllLocation()
    }

    override suspend fun addLocation(geoPoint: GeoPointDomain): Boolean = locationStorageDataSource.addLocation(geoPoint)

    override suspend fun updateLocation(geoPoint: GeoPointDomain): Boolean = locationStorageDataSource.updateLocation(geoPoint)

    override suspend fun deleteLocation(geoPoint: GeoPointDomain): Boolean = locationStorageDataSource.deleteLocation(geoPoint)

    override suspend fun deleteAllLocation(): Boolean = locationStorageDataSource.deleteAllLocation()

}