package ru.je_dog.data.location_notification.storage

interface GetLocationNotificationSettingsStorageDataSource {

    suspend fun getLocationUpdateSecondsInterval(): Int

    suspend fun getVibrationStatus(): Boolean

    suspend fun setLocationUpdateSecondsInterval(seconds: Int)

    suspend fun setVibrationStatus(status: Boolean)

}