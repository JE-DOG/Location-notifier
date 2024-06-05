package ru.je_dog.feature.notification_settings.vm

import androidx.annotation.IntRange
import ru.je_dog.core.feature.base.vm.ViewState

data class NotificationSettingsViewState(
    val vibrationIsEnabled: Boolean = true,
    @IntRange(
        from = 1,
        to = 60,
    )
    val locationUpdateSecondsInterval: Int = 5,
): ViewState
