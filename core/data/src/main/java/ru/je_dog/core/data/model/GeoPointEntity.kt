package ru.je_dog.core.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.VisibleForTesting
import ru.je_dog.core.domain.model.GeoPointDomain
import kotlin.random.Random

@Entity(
    tableName = "geo_point_table"
)
data class GeoPointEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String = "",
    val meters: Int? = null,
    val latitude: Double,
    val longitude: Double
){

    fun toDomain() = GeoPointDomain(
        id = id,
        name = name,
        meters = meters,
        latitude = latitude,
        longitude = longitude
    )

    companion object {

        fun fromDomain(
            geoPointDomain: GeoPointDomain
        ): GeoPointEntity{
            geoPointDomain.run {
                return GeoPointEntity(
                    id = id,
                    name = name,
                    latitude = latitude,
                    longitude = longitude,
                    meters = meters
                )
            }
        }

        @VisibleForTesting
        fun mock() = GeoPointEntity(
            null,
            "",
            meters = Random.nextInt(),
            Random.nextDouble(),
            Random.nextDouble()
        )

    }
}