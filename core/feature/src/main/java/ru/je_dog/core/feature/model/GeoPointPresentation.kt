package ru.je_dog.core.feature.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.jetbrains.annotations.VisibleForTesting
import ru.je_dog.core.domain.model.GeoPointDomain
import kotlin.random.Random

@Parcelize
data class GeoPointPresentation(
    val id: Int? = null,
    val name: String = "",
    val meters: Int? = null,
    val longitude: Double,
    val latitude: Double
): Parcelable {

    fun toDomain() = GeoPointDomain(
        id = id,
        name = name,
        meters = meters,
        latitude = latitude,
        longitude = longitude
    )

    companion object {

        fun fromDomain(geoPointDomain: GeoPointDomain): GeoPointPresentation = geoPointDomain.run {
            GeoPointPresentation(
                id = id,
                name = name,
                meters = meters,
                latitude = longitude,
                longitude = latitude
            )
        }

        @VisibleForTesting
        fun mock() = GeoPointPresentation(
            id = Random.nextInt(),
            name = "Some point",
            meters = Random.nextInt(),
            longitude = Random.nextDouble(),
            latitude = Random.nextDouble(),
        )

        @VisibleForTesting
        fun mockList(size: Int = 10) = List(size){
            GeoPointPresentation(
                id = it,
                name = "Some point",
                meters = Random.nextInt(),
                longitude = Random.nextDouble(),
                latitude = Random.nextDouble(),
            )
        }

    }
}