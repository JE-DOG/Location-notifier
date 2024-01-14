package ru.je_dog.data.location_list.storage

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.je_dog.core.data.model.GeoPointEntity
import ru.je_dog.core.domain.model.GeoPointDomain
import ru.je_dog.core.function.isRunWithoutException
import ru.je_dog.core.function.isRunWithoutExceptionSuspend
import ru.je_dog.data.location_list.storage.room.LocationDao

class LocationStorageDataSourceImpl(
    private val locationListDao: LocationDao
): LocationStorageDataSource {

    override fun getAllLocation(): Flow<List<GeoPointDomain>> = flow {
        val result = locationListDao.getAllLocation()
            .map { geoPointEntity ->
                geoPointEntity.toDomain()
            }

        Log.d("GetAllLocationTag","$result")

        emit(result)
    }

    override suspend fun addLocation(geoPoint: GeoPointDomain): Boolean = isRunWithoutExceptionSuspend {
        locationListDao.addLocation(GeoPointEntity.fromDomain(geoPoint))
    }

    override suspend fun updateLocation(geoPoint: GeoPointDomain): Boolean = isRunWithoutExceptionSuspend {
        locationListDao.updateLocation(GeoPointEntity.fromDomain(geoPoint))
    }

    override suspend fun deleteLocation(geoPoint: GeoPointDomain): Boolean = isRunWithoutExceptionSuspend {
        Log.d("DeleteLocationTag",geoPoint.toString())

        val result = locationListDao.deleteLocation(geoPoint.id!!)

        Log.d("DeleteLocationTag",result.toString())
    }

    override suspend fun deleteAllLocation(): Boolean = isRunWithoutExceptionSuspend {
        locationListDao.deleteAllLocation()
    }

}