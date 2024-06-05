package ru.je_dog.feature.notification_settings.vm.components

import ru.je_dog.core.feature.base.vm.reducer.Mutation

sealed interface NotificationSettingsMutation: Mutation {

    data class SetLocationUpdateSecondsInterval(
        val seconds: Int
    ): NotificationSettingsMutation

    data class SetVibrationState(
        val state: Boolean
    ): NotificationSettingsMutation

}