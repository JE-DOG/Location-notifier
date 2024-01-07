package ru.je_dog.data.location_list.di

import dagger.Module
import dagger.Provides
import ru.je_dog.data.location_list.LocationRepositoryImpl
import ru.je_dog.data.location_list.storage.LocationStorageDataSource
import ru.je_dog.data.location_list.storage.di.StorageDataSourceModule
import ru.je_dog.domain.location_list.LocationRepository
import ru.je_dog.domain.location_list.di.LocationRepositoryUseCaseModule

@Module(
    includes = [
        StorageDataSourceModule::class,
        LocationRepositoryUseCaseModule::class
    ]
)
class LocationRepositoryModule {

    @Provides
    fun provideLocationRepository(
        locationStorageDataSource: LocationStorageDataSource
    ): LocationRepository {
        return LocationRepositoryImpl(
            locationStorageDataSource
        )
    }

}