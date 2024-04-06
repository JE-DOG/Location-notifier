package ru.je_dog.domain.location_list.di

import dagger.Module
import dagger.Provides
import ru.je_dog.domain.location_list.LocationRepository
import ru.je_dog.domain.location_list.use_case.*

@Module
class LocationRepositoryUseCaseModule {

    @Provides
    fun provideAddLocationUseCase(
        locationRepository: LocationRepository
    ): AddLocationUseCase {
        return AddLocationUseCase(
            locationRepository
        )
    }

    @Provides
    fun provideDeleteAllLocationUseCase(
        locationRepository: LocationRepository
    ): DeleteAllLocationUseCase {
        return DeleteAllLocationUseCase(
            locationRepository
        )
    }

    @Provides
    fun provideDeleteLocationUseCase(
        locationRepository: LocationRepository
    ): DeleteLocationUseCase {
        return DeleteLocationUseCase(
            locationRepository
        )
    }

    @Provides
    fun provideGetAllLocationUseCase(
        locationRepository: LocationRepository
    ): GetAllLocationUseCase {
        return GetAllLocationUseCase(
            locationRepository
        )
    }

    @Provides
    fun provideUpdateLocationUseCase(
        locationRepository: LocationRepository
    ): UpdateLocationUseCase {
        return UpdateLocationUseCase(
            locationRepository
        )
    }

}