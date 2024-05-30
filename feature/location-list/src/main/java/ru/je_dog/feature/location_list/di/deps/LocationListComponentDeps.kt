package ru.je_dog.feature.location_list.di.deps

import android.content.Context
import ru.je_dog.core.feature.base.app.vm.AppViewModel
import ru.je_dog.core.feature.base.di.DaggerComponentDeps
import ru.je_dog.core.feature.base.service.location.LocationManager
import ru.je_dog.core.feature.base.service.notification.NotificationChannelService

interface LocationListComponentDeps: DaggerComponentDeps {
    val context: Context
    val appViewModel: AppViewModel
}