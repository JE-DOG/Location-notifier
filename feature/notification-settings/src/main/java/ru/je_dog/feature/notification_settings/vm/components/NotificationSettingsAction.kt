package ru.je_dog.feature.notification_settings.vm.components

import ru.je_dog.core.feature.base.vm.Action


sealed interface NotificationSettingsAction: Action {

    data class VibrationStateChange(
        val state: Boolean
    ): NotificationSettingsAction

    data class ChangeLocationUpdateSecondsInterval(
        val seconds: Int
    ): NotificationSettingsAction

}