package ru.je_dog.feature.notification_settings.di

import dagger.Component
import ru.je_dog.data.location_notification.di.LocationNotificationModule
import ru.je_dog.feature.notification_settings.di.deps.NotificationSettingsComponentDeps
import ru.je_dog.feature.notification_settings.vm.NotificationSettingsViewModel

@Component(
    modules = [
        LocationNotificationModule::class
    ],
    dependencies = [
        NotificationSettingsComponentDeps::class
    ]
)
interface NotificationSettingsComponent {

    val notificationSettingsViewModel: NotificationSettingsViewModel

    @Component.Factory
    interface Factory {
        fun create(
            deps: NotificationSettingsComponentDeps
        ): NotificationSettingsComponent
    }
}