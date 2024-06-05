package ru.je_dog.feature.notification_settings.vm.components

import ru.je_dog.core.feature.base.vm.reducer.Reducer
import ru.je_dog.feature.notification_settings.vm.NotificationSettingsViewState

class NotificationSettingsReducer: Reducer<NotificationSettingsViewState,NotificationSettingsMutation> {

    override fun invoke(
        mutation: NotificationSettingsMutation,
        currentViewState: NotificationSettingsViewState
    ): NotificationSettingsViewState {
        return when(mutation){

            is NotificationSettingsMutation.SetVibrationState -> {
                currentViewState.copy(
                    vibrationIsEnabled = mutation.state
                )
            }

            is NotificationSettingsMutation.SetLocationUpdateSecondsInterval -> {
                currentViewState.copy(
                    locationUpdateSecondsInterval = mutation.seconds
                )
            }

        }
    }
}