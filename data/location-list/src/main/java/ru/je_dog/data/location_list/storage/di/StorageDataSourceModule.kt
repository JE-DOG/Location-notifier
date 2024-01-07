package ru.je_dog.data.location_list.storage.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.je_dog.data.location_list.storage.LocationStorageDataSource
import ru.je_dog.data.location_list.storage.LocationStorageDataSourceImpl
import ru.je_dog.data.location_list.storage.room.LocationDao

@Module
internal class StorageDataSourceModule {

    @Provides
    fun provideLocationStorageDataSource(
        locationDao: LocationDao
    ):LocationStorageDataSource{
        return LocationStorageDataSourceImpl(
            locationDao
        )
    }

}