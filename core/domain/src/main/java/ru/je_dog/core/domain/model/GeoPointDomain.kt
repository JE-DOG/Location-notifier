package ru.je_dog.core.domain.model

import org.jetbrains.annotations.VisibleForTesting
import kotlin.random.Random

data class GeoPointDomain(
    val id: Int? = null,
    val name: String = "",
    val latitude: Double,
    val longitude: Double
){

    companion object {

        @VisibleForTesting
        fun mock() = GeoPointDomain(
            latitude = Random.nextDouble(),
            longitude = Random.nextDouble()
        )

    }
}
