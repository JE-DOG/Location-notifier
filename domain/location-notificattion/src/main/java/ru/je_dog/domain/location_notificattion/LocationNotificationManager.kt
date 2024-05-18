package ru.je_dog.domain.location_notificattion

import kotlinx.coroutines.flow.Flow

interface LocationNotificationManager {

    suspend fun setLocationUpdateSecondsInterval(seconds: Int)

    fun getLocationUpdateSecondsInterval(): Flow<Int>

    /**
     * @param status true = vibration is on, false = vibration is off
     * */
    suspend fun setVibrationStatus(status: Boolean)

    /**
     * @return vibration status - true = vibration is on, false = vibration is off
     * */
    fun getVibrationStatus(): Flow<Boolean>

}