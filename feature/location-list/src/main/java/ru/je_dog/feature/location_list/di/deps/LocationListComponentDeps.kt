package ru.je_dog.feature.location_list.di.deps

import android.content.Context
import ru.je_dog.core.feature.base.di.DaggerComponentDeps
import ru.je_dog.core.feature.base.location.LocationManager
import ru.je_dog.core.feature.base.notification.NotificationChannelService

interface LocationListComponentDeps: DaggerComponentDeps {

    val context: Context

}