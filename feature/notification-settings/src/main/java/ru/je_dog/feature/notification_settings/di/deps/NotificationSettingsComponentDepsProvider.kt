package ru.je_dog.feature.notification_settings.di.deps

import ru.je_dog.core.feature.base.di.DaggerDepsProvider
import kotlin.properties.Delegates

object NotificationSettingsComponentDepsProvider: DaggerDepsProvider<NotificationSettingsComponentDeps> {
    override var deps: NotificationSettingsComponentDeps by Delegates.notNull()
}