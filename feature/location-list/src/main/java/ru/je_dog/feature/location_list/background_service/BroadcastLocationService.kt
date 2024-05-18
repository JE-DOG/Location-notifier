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
import ru.je_dog.core.feature.utills.ext.distanceInMeters
import ru.je_dog.core.feature.base.service.location.LocationManagerImpl
import ru.je_dog.core.feature.base.service.notification.ForegroundNotificationChannelService
import ru.je_dog.core.feature.model.GeoPointPresentation
import ru.je_dog.core.feature.base.service.notification.NotificationChannelService
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
    private val vibrationService by lazy {
        VibrationServiceImpl(baseContext)
    }
    private val lifecycleScope = CoroutineScope(Dispatchers.Main)

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val goalGeoPoint: GeoPointPresentation = intent.getGoalLocation()
        val foregroundNotification = foregroundNotificationChannel.getStartBroadcastNotification()

        startForeground(
            FOREGROUND_ACTIVE_NOTIFICATION_ID,
            foregroundNotification,
        )

        locationManager.broadcastLocation(5)
            .catch {
                Toast.makeText(baseContext, ru.je_dog.core.feature.R.string.something_went_wrong, Toast.LENGTH_SHORT).show()
                stopSelf()
            }
            .onEach { geoPoint ->
                val metersToGoal = geoPoint distanceInMeters goalGeoPoint

                if (metersToGoal <= goalGeoPoint.meters!!){
                    val getToLocationNotification = foregroundNotificationChannel.getGetToLocationNotification(goalGeoPoint)

                    foregroundNotificationChannel.notify(
                        GET_TO_LOCATION_NOTIFICATION_ID,
                        getToLocationNotification,
                    )
                    vibrationService.vibrate(1.seconds)
                    stopSelf()
                }else {
                    val distanceToGoalNotification = foregroundNotificationChannel.getDistanceToGoalNotification(
                        goalGeoPoint = goalGeoPoint,
                        metersToGoal = metersToGoal
                    )

                    foregroundNotificationChannel.notify(
                        FOREGROUND_ACTIVE_NOTIFICATION_ID,
                        distanceToGoalNotification,
                    )
                }
            }
            .launchIn(lifecycleScope)

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleScope.cancel()
    }

    private fun NotificationChannelService.getGetToLocationNotification(
        goalGeoPoint: GeoPointPresentation
    ): Notification {
        val notificationTitle = baseContext.getString(
            ru.je_dog.core.feature.R.string.notification_location_goal_notify_title,
            goalGeoPoint.name
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
        goalGeoPoint: GeoPointPresentation,
        metersToGoal: Int,
    ): Notification {
        val notificationTitle = baseContext.getString(
            ru.je_dog.core.feature.R.string.notification_location_active_notify_title,
            goalGeoPoint.name
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

    private fun Intent.getGoalLocation(): GeoPointPresentation {
        val extraKey = baseContext.getString(ru.je_dog.core.feature.R.string.goal_geo_point_extra_key)

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
            geoPoint: GeoPointPresentation
        ): Boolean{
            return if (!context.isServiceActive(BroadcastLocationService::class.java)){
                val intent = Intent(context, BroadcastLocationService::class.java).apply {
                    val geoPointKey = context.getString(ru.je_dog.core.feature.R.string.goal_geo_point_extra_key)
                    putExtra(geoPointKey,geoPoint)
                }

                context.startService(intent)
                true
            } else false
        }
    }
}