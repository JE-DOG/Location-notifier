package ru.je_dog.core.feature.base.notification

import android.Manifest
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import ru.je_dog.core.feature.R
import ru.je_dog.core.feature.ext.checkPermission

abstract class NotificationChannelService(
    private val context: Context
) {

    abstract val channel: NotificationChannel
    private val notificationService: NotificationManagerCompat = NotificationManagerCompat.from(context)

    fun notify(
        notificationId: Int,
        notification: Notification
    ){

        context.checkPermission(
            PERMISSION,
            onPermissionDenied = ::onPermissionDenied,
        ){
            onPermissionGranted(
                notificationId,
                notification
            )
        }

    }

    fun getNotificationBuilder(
        notification: Notification.Builder = Notification.Builder(context),
        notificationBuilder: Notification.Builder.() -> Notification.Builder
    ): Notification.Builder {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
            notification.apply {
                setChannelId(channel.id)
                setSmallIcon(R.drawable.ic_launcher_foreground)
            }
        }

        return notificationBuilder(notification)
    }

    protected open fun onPermissionDenied(){
        Toast.makeText(context, R.string.notification_permission_denied, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("MissingPermission")
    protected open fun onPermissionGranted(
        notificationId: Int,
        notification: Notification
    ){
        notificationService.notify(
            notificationId,
            notification
        )
    }

    companion object {

        const val PERMISSION = Manifest.permission.POST_NOTIFICATIONS

    }
}