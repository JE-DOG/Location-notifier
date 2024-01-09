package ru.je_dog.core.feature.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.je_dog.core.domain.model.GeoPointDomain

@Parcelize
data class GeoPointPresentation(
    val id: Int? = null,
    val name: String = "",
    val longitude: Double,
    val latitude: Double
): Parcelable {

    fun toDomain() = GeoPointDomain(
        id,
        name,
        latitude,
        longitude
    )

    companion object {

        fun fromDomain(geoPointDomain: GeoPointDomain): GeoPointPresentation = geoPointDomain.run {
            GeoPointPresentation(
                id,
                name,
                longitude,
                latitude
            )
        }

    }
}