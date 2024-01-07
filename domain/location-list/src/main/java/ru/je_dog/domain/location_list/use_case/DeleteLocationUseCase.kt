package ru.je_dog.domain.location_list.use_case

import ru.je_dog.core.domain.model.GeoPointDomain
import ru.je_dog.domain.location_list.LocationRepository

class DeleteLocationUseCase(
    private val locationRepository: LocationRepository
) {

    suspend fun execute(geoPoint: GeoPointDomain) = locationRepository.deleteLocation(geoPoint)

}