package ru.je_dog.feature.notification_settings.di.deps

import android.content.Context
import ru.je_dog.core.feature.base.di.DaggerComponentDeps

interface NotificationSettingsComponentDeps: DaggerComponentDeps {
    val context: Context
}