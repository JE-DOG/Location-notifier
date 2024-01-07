package ru.je_dog.data.location_list.storage.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
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

    @Insert
    fun addLocation(geoPoint: GeoPointEntity): Boolean

    @Update
    fun updateLocation(geoPoint: GeoPointEntity): Boolean

    @Delete
    fun deleteLocation(geoPoint: GeoPointEntity): Boolean

    @Query("DELETE FROM geo_point_table")
    fun deleteAllLocation(): Boolean

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

        override fun addLocation(geoPoint: GeoPointEntity): Boolean {
            return if (isSuccess){
                true
            }else{
                throw Exception()
            }
        }

        override fun updateLocation(geoPoint: GeoPointEntity): Boolean {
            return if (isSuccess){
                true
            }else{
                throw Exception()
            }
        }

        override fun deleteLocation(geoPoint: GeoPointEntity): Boolean {
            return if (isSuccess){
                true
            }else{
                throw Exception()
            }
        }

        override fun deleteAllLocation(): Boolean {
            return if (isSuccess){
                true
            }else{
                throw Exception()
            }
        }

    }

}