package ru.je_dog.data.location_notification.storage

import android.content.Context
import androidx.core.content.edit
import ru.je_dog.core.data.data_source.SharedPreferencesDataSource

class GetLocationNotificationSettingsStorageDataSourceImpl(
    context: Context
): SharedPreferencesDataSource(
    context,
    GET_LOCATION_NOTIFICATION_SETTINGS_NAME,
), GetLocationNotificationSettingsStorageDataSource {

    override suspend fun getLocationUpdateSecondsInterval(): Int {
        return sharedPreferences.getInt(
            GET_LOCATION_NOTIFICATION_SETTINGS_LOCATION_UPDATE_INTERVAL_KEY,
            5
        )
    }

    override suspend fun setLocationUpdateSecondsInterval(seconds: Int) {
        sharedPreferences.edit {
            putInt(
                GET_LOCATION_NOTIFICATION_SETTINGS_LOCATION_UPDATE_INTERVAL_KEY,
                seconds
            )
        }
    }

    override suspend fun setVibrationStatus(status: Boolean) {
        sharedPreferences.edit {
            putBoolean(
                GET_LOCATION_NOTIFICATION_SETTINGS_VIBRATION_STATUS_KEY,
                status
            )
        }
    }

    override suspend fun getVibrationStatus(): Boolean {
        return sharedPreferences.getBoolean(
            GET_LOCATION_NOTIFICATION_SETTINGS_VIBRATION_STATUS_KEY,
            true
        )
    }

    companion object {
        private const val GET_LOCATION_NOTIFICATION_SETTINGS_NAME = "GET_LOCATION_NOTIFICATION_SETTINGS_NAME"
        private const val GET_LOCATION_NOTIFICATION_SETTINGS_VIBRATION_STATUS_KEY = "GET_LOCATION_NOTIFICATION_SETTINGS_VIBRATION_INTERVAL_KEY"
        private const val GET_LOCATION_NOTIFICATION_SETTINGS_LOCATION_UPDATE_INTERVAL_KEY = "GET_LOCATION_NOTIFICATION_SETTINGS_LOCATION_UPDATE_INTERVAL_KEY"
    }
}