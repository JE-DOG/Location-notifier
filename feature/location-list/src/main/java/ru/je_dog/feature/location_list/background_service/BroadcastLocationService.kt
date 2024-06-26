package ru.je_dog.feature.location_list.background_service

import android.app.Notification
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.retry
import ru.je_dog.core.feature.utills.ext.distanceInMeters
import ru.je_dog.core.feature.base.service.location.LocationManagerImpl
import ru.je_dog.core.feature.base.service.notification.ForegroundNotificationChannelService
import ru.je_dog.core.feature.model.GeoPointPresentation
import ru.je_dog.core.feature.base.service.notification.NotificationChannelService
import ru.je_dog.core.feature.base.service.vibration.VibrationService
import ru.je_dog.core.feature.base.service.vibration.VibrationServiceImpl
import ru.je_dog.core.feature.utills.ext.isServiceActive
import ru.je_dog.core.feature.utills.ext.seconds

class BroadcastLocationService: Service() {

    private val locationManager by lazy { LocationManagerImpl(baseContext) }
    private val foregroundNotificationChannel: NotificationChannelService by lazy {
        ForegroundNotificationChannelService(baseContext).apply {
            init()
        }
    }
    private val vibrationService: VibrationService by lazy {
        VibrationServiceImpl(baseContext)
    }
    private val lifecycleScope = CoroutineScope(Dispatchers.Main)

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val goalGeoPoint: GeoPointPresentation = intent.goalLocation
        val vibrationState = intent.vibrationState
        val locationUpdateSecondsInterval = intent.locationUpdateSecondsInterval
        val foregroundNotification = foregroundNotificationChannel.getStartBroadcastNotification()

        startForeground(
            FOREGROUND_ACTIVE_NOTIFICATION_ID,
            foregroundNotification,
        )

        locationManager.broadcastLocation(
            secondsInterval = locationUpdateSecondsInterval
        )
            .catch {
                Toast.makeText(baseContext, ru.je_dog.core.feature.R.string.something_went_wrong, Toast.LENGTH_SHORT).show()
                stopSelf()
            }
            .onEach { geoPoint ->
                val metersToGoal = geoPoint distanceInMeters goalGeoPoint

                if (metersToGoal <= goalGeoPoint.meters!!){
                    actionOnGetToGoal(
                        goalName = goalGeoPoint.name,
                        vibrationState = vibrationState
                    )
                }else {
                    actionOnProgress(
                        goalGeoPoint.name,
                        metersToGoal,
                    )
                }
            }
            .launchIn(lifecycleScope)

        return START_NOT_STICKY
    }

    private fun actionOnProgress(
        goalName: String,
        metersToGoal: Int,
    ) {
        val distanceToGoalNotification = foregroundNotificationChannel.getDistanceToGoalNotification(
            goalName = goalName,
            metersToGoal = metersToGoal
        )

        foregroundNotificationChannel.notify(
            notificationId = FOREGROUND_ACTIVE_NOTIFICATION_ID,
            notification = distanceToGoalNotification,
        )
    }

    private fun actionOnGetToGoal(
        goalName: String,
        vibrationState: Boolean,
    ) {
        val getToLocationNotification = foregroundNotificationChannel.getGetToLocationNotification(
            goalName = goalName,
        )

        foregroundNotificationChannel.notify(
            notificationId = GET_TO_LOCATION_NOTIFICATION_ID,
            notification = getToLocationNotification,
        )
        if (vibrationState){
            vibrationService.vibrate(1.seconds)
        }
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleScope.cancel()
    }

    private fun NotificationChannelService.getGetToLocationNotification(
        goalName: String,
    ): Notification {
        val notificationTitle = baseContext.getString(
            ru.je_dog.core.feature.R.string.notification_location_goal_notify_title,
            goalName
        )
        val notification = getNotificationBuilder {
            setContentTitle(notificationTitle)
        }
            .build()

        return notification
    }

    private fun NotificationChannelService.getStartBroadcastNotification(): Notification {
        return getNotificationBuilder {
            val title = baseContext.getString(ru.je_dog.core.feature.R.string.notification_location_start_notify_title)

            setOngoing(true)
            setOnlyAlertOnce(true)
            setContentTitle(title)
        }
            .build()
    }

    private fun NotificationChannelService.getDistanceToGoalNotification(
        goalName: String,
        metersToGoal: Int,
    ): Notification {
        val notificationTitle = baseContext.getString(
            ru.je_dog.core.feature.R.string.notification_location_active_notify_title,
            goalName
        )
        val notificationDescription = baseContext.getString(
            ru.je_dog.core.feature.R.string.notification_location_active_notify_description,
            metersToGoal
        )

        val notification = getNotificationBuilder {
            setContentTitle(notificationTitle)
            setContentText(notificationDescription)
            setOngoing(true)
            setOnlyAlertOnce(true)
        }
            .build()

        return notification
    }

    private val Intent.locationUpdateSecondsInterval: Int get() {
        val extraKey = baseContext.getString(ru.je_dog.core.feature.R.string.location_broadcast_location_update_seconds_interval_extra_key)

        return getIntExtra(extraKey,5)
    }

    private val Intent.vibrationState: Boolean get() {
        val extraKey = baseContext.getString(ru.je_dog.core.feature.R.string.location_broadcast_vibration_state_extra_key)

        return getBooleanExtra(extraKey,true)
    }

    private val Intent.goalLocation: GeoPointPresentation get() {
        val extraKey = baseContext.getString(ru.je_dog.core.feature.R.string.location_broadcast_goal_geo_point_extra_key)

        return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.TIRAMISU){
            getParcelableExtra(extraKey,GeoPointPresentation::class.java)!!
        }else {
            getParcelableExtra(extraKey)!!
        }
    }

    companion object {
        private const val FOREGROUND_ACTIVE_NOTIFICATION_ID = 2
        private const val GET_TO_LOCATION_NOTIFICATION_ID = 1

        /**
         * @param geoPoint Точка до которой будет активен слушатель
         * @return true - Нет активного сервиса и был создан новый, false - Есть активный сервис, новый серис не создан
         */
        fun startBroadcast(
            context: Context,
            geoPoint: GeoPointPresentation,
            locationUpdateSecondsInterval: Int = 5,
            vibrationState: Boolean = true,
        ): Boolean {
            return if (!context.isServiceActive(BroadcastLocationService::class.java)){
                val intent = Intent(context, BroadcastLocationService::class.java).apply {
                    val locationUpdateSecondsIntervalExtraKey = context.getString(ru.je_dog.core.feature.R.string.location_broadcast_location_update_seconds_interval_extra_key)
                    putExtra(locationUpdateSecondsIntervalExtraKey,locationUpdateSecondsInterval)

                    val vibrationStateExtraKey = context.getString(ru.je_dog.core.feature.R.string.location_broadcast_vibration_state_extra_key)
                    putExtra(vibrationStateExtraKey,vibrationState)

                    val geoPointKey = context.getString(ru.je_dog.core.feature.R.string.location_broadcast_goal_geo_point_extra_key)
                    putExtra(geoPointKey,geoPoint)
                }

                context.startService(intent)
                true
            } else {
                false
            }
        }
    }
}