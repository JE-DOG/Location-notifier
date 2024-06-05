package ru.je_dog.location_notifier.ui.permission.vm

data class PermissionViewState(
    val locationIsGranted: Boolean = false,
    val notificationIsGranted: Boolean? = null,
)