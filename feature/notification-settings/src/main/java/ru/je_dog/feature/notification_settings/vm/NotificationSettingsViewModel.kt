package ru.je_dog.feature.notification_settings.vm

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ru.je_dog.core.feature.base.vm.BaseViewModel
import ru.je_dog.core.feature.base.vm.reducer.Reducer
import ru.je_dog.domain.location_notificattion.LocationNotificationManager
import ru.je_dog.feature.notification_settings.vm.components.NotificationSettingsAction
import ru.je_dog.feature.notification_settings.vm.components.NotificationSettingsMutation
import ru.je_dog.feature.notification_settings.vm.components.NotificationSettingsReducer
import javax.inject.Inject

class NotificationSettingsViewModel @Inject constructor(
    private val notificationManager: LocationNotificationManager.Mixed,
): BaseViewModel<NotificationSettingsViewState, NotificationSettingsMutation, Reducer<NotificationSettingsViewState, NotificationSettingsMutation>>(
    reducer = NotificationSettingsReducer()
) {

    private val _state = MutableStateFlow(NotificationSettingsViewState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            launch {
                val currentLocationUpdateSecondInterval = notificationManager.getLocationUpdateSecondsInterval().first()

                NotificationSettingsMutation.SetLocationUpdateSecondsInterval(currentLocationUpdateSecondInterval)
                    .reduce(_state)
            }

            val vibrationState = notificationManager.getVibrationStatus().first()
            NotificationSettingsMutation.SetVibrationState(vibrationState)
                .reduce(_state)
        }
    }

    fun action(action: NotificationSettingsAction){
        when(action){
            is NotificationSettingsAction.VibrationStateChange -> {
                vibrationStateChanged(
                    state = action.state
                )
            }

            is NotificationSettingsAction.ChangeLocationUpdateSecondsInterval -> {
                locationUpdateSecondIntervalChanged(
                    seconds = action.seconds
                )
            }
        }
    }

    private fun locationUpdateSecondIntervalChanged(seconds: Int) = viewModelScope.launch {
        NotificationSettingsMutation.SetLocationUpdateSecondsInterval(seconds)
            .reduce(_state)
        notificationManager.setLocationUpdateSecondsInterval(seconds)
    }

    private fun vibrationStateChanged(state: Boolean) = viewModelScope.launch {
        NotificationSettingsMutation.SetVibrationState(state)
            .reduce(_state)
        notificationManager.setVibrationStatus(state)
    }

}