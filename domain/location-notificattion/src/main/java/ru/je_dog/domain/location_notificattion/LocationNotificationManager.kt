package ru.je_dog.domain.location_notificattion

import kotlinx.coroutines.flow.Flow

interface LocationNotificationManager {

    interface Write {
        suspend fun setLocationUpdateSecondsInterval(seconds: Int)
        /**
         * @param status true = vibration is on, false = vibration is off
         * */
        suspend fun setVibrationStatus(status: Boolean)
    }

    interface Read {
        fun getLocationUpdateSecondsInterval(): Flow<Int>

        /**
         * @return vibration status - true = vibration is on, false = vibration is off
         * */
        fun getVibrationStatus(): Flow<Boolean>

    }

    interface Mixed: Read, Write
}