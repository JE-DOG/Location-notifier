package ru.je_dog.domain.location_list.use_case

import kotlinx.coroutines.flow.Flow
import ru.je_dog.core.domain.model.GeoPointDomain
import ru.je_dog.domain.location_list.LocationRepository

class GetAllLocationUseCase(
    private val locationRepository: LocationRepository
) {

    fun execute(): Flow<List<GeoPointDomain>> {
        return locationRepository.getAllLocation()
    }

}