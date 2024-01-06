package ru.je_dog.core.feature.ext

import android.content.Context
import android.content.pm.PackageManager
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