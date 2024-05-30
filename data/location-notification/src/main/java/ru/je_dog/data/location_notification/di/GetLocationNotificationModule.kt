package ru.je_dog.data.location_notification.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.je_dog.data.location_notification.GetLocationNotificationManagerImpl
import ru.je_dog.data.location_notification.storage.GetLocationNotificationSettingsStorageDataSource
import ru.je_dog.data.location_notification.storage.GetLocationNotificationSettingsStorageDataSourceImpl
import ru.je_dog.domain.location_notificattion.LocationNotificationManager

@Module(
    includes = [
        GetLocationNotificationBindModule::class
    ]
)
class GetLocationNotificationModule {

    @Provides
    fun provideGetLocationNotificationSettingsStorageDataSource(
        context: Context
    ): GetLocationNotificationSettingsStorageDataSource {
        return GetLocationNotificationSettingsStorageDataSourceImpl(
            context = context
        )
    }

    @Provides
    fun provideGetLocationNotificationSettingsManager(
        getLocationNotificationSettingsStorageDataSource: GetLocationNotificationSettingsStorageDataSource
    ): LocationNotificationManager.Mixed {
        return GetLocationNotificationManagerImpl(
            storageDataSource = getLocationNotificationSettingsStorageDataSource
        )
    }

}

@Module
interface GetLocationNotificationBindModule {
    @Binds
    fun provideGetLocationNotificationManagerWrite(mixed: LocationNotificationManager.Mixed): LocationNotificationManager.Read
    @Binds
    fun provideGetLocationNotificationManagerRead(mixed: LocationNotificationManager.Mixed): LocationNotificationManager.Write
}