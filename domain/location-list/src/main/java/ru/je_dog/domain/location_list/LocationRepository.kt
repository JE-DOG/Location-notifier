package ru.je_dog.domain.location_list

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jetbrains.annotations.VisibleForTesting
import ru.je_dog.core.domain.model.GeoPointDomain
import kotlin.random.Random

interface LocationRepository {

    fun getAllLocation(): Flow<List<GeoPointDomain>>

    suspend fun addLocation(geoPoint: GeoPointDomain): Boolean

    suspend fun updateLocation(geoPoint: GeoPointDomain): Boolean

    suspend fun deleteLocation(geoPoint: GeoPointDomain): Boolean

    suspend fun deleteAllLocation(): Boolean

    @VisibleForTesting
    class Mock(
        var isSuccess: Boolean = true
    ): LocationRepository{

        override fun getAllLocation(): Flow<List<GeoPointDomain>> = flow {

            if (isSuccess){
                val result = List(10){
                    GeoPointDomain.mock()
                }

                emit(result)
            }else {
                emit(emptyList())
            }

        }

        override suspend fun addLocation(geoPoint: GeoPointDomain): Boolean {
            return isSuccess
        }

        override suspend fun updateLocation(geoPoint: GeoPointDomain): Boolean {

            return isSuccess
        }

        override suspend fun deleteLocation(geoPoint: GeoPointDomain): Boolean {
            return isSuccess
        }

        override suspend fun deleteAllLocation(): Boolean {
            return isSuccess
        }

    }

}