package ru.je_dog.data.location_notification

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.je_dog.data.location_notification.storage.GetLocationNotificationSettingsStorageDataSource
import ru.je_dog.domain.location_notificattion.LocationNotificationManager

class GetLocationNotificationManagerImpl(
    val storageDataSource: GetLocationNotificationSettingsStorageDataSource
): LocationNotificationManager.Mixed {

    override suspend fun setLocationUpdateSecondsInterval(seconds: Int) {
        storageDataSource.setLocationUpdateSecondsInterval(
            seconds = seconds
        )
    }

    override suspend fun setVibrationStatus(status: Boolean) {
        storageDataSource.setVibrationStatus(
            status = status
        )
    }

    override fun getLocationUpdateSecondsInterval(): Flow<Int> = flow {
        val result = storageDataSource.getLocationUpdateSecondsInterval()
        emit(result)
    }

    override fun getVibrationStatus(): Flow<Boolean> = flow {
        val result = storageDataSource.getVibrationStatus()
        emit(result)
    }
}