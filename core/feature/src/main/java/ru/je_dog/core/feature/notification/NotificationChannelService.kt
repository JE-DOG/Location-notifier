package ru.je_dog.core.feature.notification

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
        notification: Notification.Builder.() -> Notification
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

    protected open fun onPermissionDenied(){
        Toast.makeText(context, R.string.notification_permission_denied, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("MissingPermission")
    protected open fun onPermissionGranted(
        notificationId: Int,
        notification: Notification.Builder.() -> Notification
    ){

        val notificationBuilder = Notification.Builder(context).apply {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
                setChannelId(channel.id)
            }
        }

        notificationService.notify(
            notificationId,
            notification(notificationBuilder)
        )
    }

    companion object {

        const val PERMISSION = Manifest.permission.POST_NOTIFICATIONS

    }
}