package ru.je_dog.data.location_list.storage.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import ru.je_dog.data.location_list.storage.LocationStorageDataSource
import ru.je_dog.data.location_list.storage.LocationStorageDataSourceImpl
import ru.je_dog.data.location_list.storage.room.LocationDao
import ru.je_dog.data.location_list.storage.room.LocationDataBase

@Module
internal class StorageDataSourceModule {

    @Provides
    fun provideLocationDataBase(
        context: Context
    ): LocationDataBase {
        return Room
            .databaseBuilder(
                context,
                LocationDataBase::class.java,
                "database"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideLocationDao(
        locationDataBase: LocationDataBase
    ): LocationDao {
        return locationDataBase.locationDao()
    }

    @Provides
    fun provideLocationStorageDataSource(
        locationDao: LocationDao
    ):LocationStorageDataSource{
        return LocationStorageDataSourceImpl(
            locationDao
        )
    }

}