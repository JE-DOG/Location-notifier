package ru.je_dog.domain.location_list.use_case

import ru.je_dog.domain.location_list.LocationRepository

class DeleteAllLocationUseCase(
    private val locationRepository: LocationRepository
) {

    suspend fun execute() = locationRepository.deleteAllLocation()

}