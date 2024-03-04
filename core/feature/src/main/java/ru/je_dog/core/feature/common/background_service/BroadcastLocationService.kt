package ru.je_dog.core.feature.common.background_service

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.je_dog.core.feature.ext.lineMeters
import ru.je_dog.core.feature.common.location.LocationManagerImpl
import ru.je_dog.core.feature.common.notification.ForegroundNotificationChannelService
import ru.je_dog.core.feature.model.GeoPointPresentation
import ru.je_dog.core.feature.common.notification.NotificationChannelService
import java.lang.StringBuilder

class BroadcastLocationService: Service() {

    private val locationManager by lazy { LocationManagerImpl(baseContext) }
    private val foregroundNotificationChannel: NotificationChannelService by lazy {
        ForegroundNotificationChannelService(baseContext).apply {
            createChannel()
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val goalGeoPoint: GeoPointPresentation = intent.run {
            val extraKey = baseContext.getString(ru.je_dog.core.feature.R.string.goal_geo_point_extra_key)

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.TIRAMISU){
                getParcelableExtra(extraKey,GeoPointPresentation::class.java)!!
            }else {
                getParcelableExtra(extraKey)!!
            }
        }
        val foregroundNotification = foregroundNotificationChannel.getNotificationBuilder {
            val title = baseContext.getString(ru.je_dog.core.feature.R.string.notification_location_start_notify_title)

            setOnlyAlertOnce(true)
            setContentTitle(title)
        }

        startForeground(
            FOREGROUND_ACTIVE_NOTIFICATION_ID,
            foregroundNotification.build()
        )

        CoroutineScope(Dispatchers.IO).launch {

            locationManager.broadcastLocation(5)
                .catch {
                    withContext(Dispatchers.Main){
                        Toast.makeText(baseContext, ru.je_dog.core.feature.R.string.something_went_wrong, Toast.LENGTH_SHORT).show()
                    }
                    stopSelf()
                }
                .collect { geoPoint ->
                    val metersToGoal = geoPoint lineMeters goalGeoPoint

                    if (metersToGoal <= goalGeoPoint.meters!!){
                        val notificationTitle = baseContext.getString(ru.je_dog.core.feature.R.string.notification_location_goal_notify_title)
                        val notification = foregroundNotificationChannel.getNotificationBuilder {
                            setContentTitle(notificationTitle)
                        }
                            .build()

                        foregroundNotificationChannel.notify(
                            FOREGROUND_ACTIVE_NOTIFICATION_ID,
                            notification
                        )
                        withContext(Dispatchers.Main){
                            Toast.makeText(baseContext, notificationTitle, Toast.LENGTH_SHORT).show()
                        }
                        stopSelf()
                    }else {
                        val notificationTitle = run {
                            val text = baseContext.getString(ru.je_dog.core.feature.R.string.notification_location_active_notify_title)
                            "$text $metersToGoal"
                        }
                        val notification = foregroundNotificationChannel.getNotificationBuilder {
                            setContentTitle(
                                notificationTitle
                            )
                            setOnlyAlertOnce(true)
                        }
                            .build()

                        foregroundNotificationChannel.notify(
                            FOREGROUND_ACTIVE_NOTIFICATION_ID,
                            notification
                        )
                    }

                }

        }

        return START_NOT_STICKY
    }

    companion object {

        const val FOREGROUND_ACTIVE_NOTIFICATION_ID = 2

    }
}