package ru.je_dog.core.domain.model

import org.jetbrains.annotations.VisibleForTesting
import kotlin.random.Random

data class GeoPointDomain(
    val latitude: Double,
    val longitude: Double
){

    companion object {

        @VisibleForTesting
        fun mock() = GeoPointDomain(
            Random.nextDouble(),
            Random.nextDouble()
        )

    }
}
