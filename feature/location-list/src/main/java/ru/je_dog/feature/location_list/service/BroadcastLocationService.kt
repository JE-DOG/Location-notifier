package ru.je_dog.feature.location_list.service

import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import ru.je_dog.core.feature.ext.lineMeters
import ru.je_dog.core.feature.base.location.LocationManagerImpl
import ru.je_dog.core.feature.base.notification.ForegroundNotificationChannelService
import ru.je_dog.core.feature.model.GeoPointPresentation
import ru.je_dog.core.feature.base.notification.NotificationChannelService
import java.lang.StringBuilder

class BroadcastLocationService: Service() {

    private val locationManager by lazy { LocationManagerImpl(baseContext) }
    private val foregroundNotificationChannel: NotificationChannelService by lazy { ForegroundNotificationChannelService(baseContext) }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        Log.d("BroadcastTag","Sometihng in the way")

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

        foregroundNotificationChannel.createChannel()

        startForeground(
            FOREGROUND_ACTIVE_NOTIFICATION_ID,
            foregroundNotification.build()
        )

        CoroutineScope(Dispatchers.IO).launch {

            locationManager.broadcastLocation(5)
                .catch {
                    Toast.makeText(baseContext, ru.je_dog.core.feature.R.string.something_went_wrong, Toast.LENGTH_SHORT).show()
                    stopSelf()
                }
                .collect { geoPoint ->

                    val metersToGoal = geoPoint lineMeters goalGeoPoint

                    if (metersToGoal <= goalGeoPoint.meters!!){
                        val notificationTitle = baseContext.getString(ru.je_dog.core.feature.R.string.notification_location_goal_notify_title)
                        val notification = foregroundNotificationChannel.getNotificationBuilder {
                            setContentTitle(notificationTitle)
                            setOnlyAlertOnce(false)
                            setVibrate(
                                longArrayOf(1000,1000,1000,1000)
                            )
                        }
                            .build()

                        foregroundNotificationChannel.notify(
                            FOREGROUND_ACTIVE_NOTIFICATION_ID,
                            notification
                        )
                        Toast.makeText(baseContext, notificationTitle, Toast.LENGTH_SHORT).show()
                        stopSelf()
                    }else {
                        val notificationTitle = StringBuilder().apply {
                            val text = baseContext.getString(ru.je_dog.core.feature.R.string.notification_location_active_notify_title)
                            append(text)
                            append(metersToGoal)
                        }
                        val notification = foregroundNotificationChannel.getNotificationBuilder {
                            setContentTitle(
                                notificationTitle.toString()
                            )
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