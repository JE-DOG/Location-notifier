package ru.je_dog.feature.location_list.di.deps

import android.content.Context
import ru.je_dog.core.feature.common.di.DaggerComponentDeps
import ru.je_dog.core.feature.common.location.LocationManager
import ru.je_dog.core.feature.common.notification.NotificationChannelService

interface LocationListComponentDeps: DaggerComponentDeps {

    val context: Context

}