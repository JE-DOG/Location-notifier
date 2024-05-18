package ru.je_dog.core.feature.base.service.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi

class ForegroundNotificationChannelService(
    context: Context
): NotificationChannelService(context) {

    @RequiresApi(Build.VERSION_CODES.O)
    override val channel: NotificationChannel = NotificationChannel(
        CHANNEL_ID,
        NAME,
        NotificationManager.IMPORTANCE_HIGH
    )

    companion object {

        const val CHANNEL_ID = "FOREGROUND_NOTIFICATION_ID"
        const val NAME = "Location notifier"

    }
}