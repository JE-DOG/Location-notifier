package ru.je_dog.domain.location_list.use_case

import ru.je_dog.domain.location_list.LocationRepository

class GetAllLocationUseCase(
    private val locationRepository: LocationRepository
) {

    fun execute() = locationRepository.getAllLocation()

}