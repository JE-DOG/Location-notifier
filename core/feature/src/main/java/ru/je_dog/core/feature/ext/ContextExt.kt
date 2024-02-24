package ru.je_dog.core.feature.ext

import android.app.ActivityManager
import android.app.ActivityManager.RunningServiceInfo
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat

fun Context.checkPermission(
    permission: String,
    onPermissionDenied: () -> Unit = {},
    onPermissionGranted: () -> Unit
){

    if (ActivityCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        onPermissionGranted()
    }else{
        onPermissionDenied()
    }

}

fun<S: Service> Context.isServiceActive(serviceClass: Class<S>): Boolean {

    val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    for (service: RunningServiceInfo in activityManager.getRunningServices(Int.MAX_VALUE)){
        Log.d("ServiceNames",service.service.className)
        Log.d("ServiceNames",serviceClass.name)
        if (service.service.className == serviceClass.name){
            return true
        }
    }

    return false
}