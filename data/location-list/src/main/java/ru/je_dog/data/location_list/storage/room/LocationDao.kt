package ru.je_dog.data.location_list.storage.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jetbrains.annotations.VisibleForTesting
import ru.je_dog.core.data.model.GeoPointEntity
import ru.je_dog.core.domain.model.GeoPointDomain
import ru.je_dog.data.location_list.storage.LocationStorageDataSource

@Dao
interface LocationDao {

    @Query("SELECT * FROM geo_point_table")
    fun getAllLocation(): List<GeoPointEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLocation(geoPoint: GeoPointEntity)

    @Update
    suspend fun updateLocation(geoPoint: GeoPointEntity)

    @Query("DELETE FROM geo_point_table WHERE id = :geoPointId")
    suspend fun deleteLocation(geoPointId: Int): Int

    @Query("DELETE FROM geo_point_table")
    suspend fun deleteAllLocation()

    @VisibleForTesting
    class Mock(
        var isSuccess: Boolean = true
    ): LocationDao {

        override fun getAllLocation(): List<GeoPointEntity> {
            return if (isSuccess){
                List(10){
                    GeoPointEntity.mock()
                }
            }else {
                emptyList()
            }
        }

        override suspend fun addLocation(geoPoint: GeoPointEntity) {
            if (!isSuccess){
                throw Exception()
            }
        }

        override suspend fun updateLocation(geoPoint: GeoPointEntity) {
            if (!isSuccess){
                throw Exception()
            }
        }

        override suspend fun deleteLocation(geoPointId: Int): Int {
            if (!isSuccess){
                throw Exception()
            }
            return 1
        }

        override suspend fun deleteAllLocation() {
            if (!isSuccess){
                throw Exception()
            }
        }

    }

}