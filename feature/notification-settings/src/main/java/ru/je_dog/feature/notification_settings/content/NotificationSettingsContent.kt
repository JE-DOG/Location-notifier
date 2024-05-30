package ru.je_dog.feature.notification_settings.content

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jetbrains.annotations.VisibleForTesting
import ru.je_dog.core.feature.base.ui.theme.LocationNotifierPreviewTheme
import ru.je_dog.feature.notification_settings.content.components.SwitchSettingItem
import ru.je_dog.feature.notification_settings.vm.NotificationSettingsViewState
import ru.je_dog.feature.notification_settings.vm.components.NotificationSettingsAction
import kotlin.math.roundToInt

//region Preview
@VisibleForTesting
@Preview
@Composable
private fun NotificationContentPreview() {
    LocationNotifierPreviewTheme {
        NotificationContent(
            state = NotificationSettingsViewState(),
            sendAction = {}
        )
    }
}
//endregion

@Composable
fun NotificationContent(
    modifier: Modifier = Modifier,
    state: NotificationSettingsViewState,
    sendAction: (NotificationSettingsAction) -> Unit,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Vibration(
            vibrationIsEnabled = state.vibrationIsEnabled,
            sendAction = sendAction,
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 10.dp)
        )

        SetLocationUpdateSecondsInterval(
            locationUpdateSecondsInterval = state.locationUpdateSecondsInterval,
            sendAction = sendAction
        )
    }
}

@Composable
private fun Vibration(
    vibrationIsEnabled: Boolean,
    sendAction: (NotificationSettingsAction) -> Unit,
) {
    SwitchSettingItem(
        title = stringResource(id = ru.je_dog.core.feature.R.string.notification_settings_screen_vibration),
        state = vibrationIsEnabled,
        onValueChange = { state ->
            val action = NotificationSettingsAction.VibrationStateChange(
                state = state
            )
            sendAction(action)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SetLocationUpdateSecondsInterval(
    locationUpdateSecondsInterval: Int,
    sendAction: (NotificationSettingsAction) -> Unit,
) {
    val thumbInteractionSource = remember {
        MutableInteractionSource()
    }

    Text(
        text = stringResource(id = ru.je_dog.core.feature.R.string.notification_settings_screen_location_update_seconds_interval),
        style = MaterialTheme.typography.titleMedium
    )

    Slider(
        value = locationUpdateSecondsInterval.toFloat(),
        onValueChange = { seconds ->
            val action = NotificationSettingsAction.ChangeLocationUpdateSecondsInterval(
                seconds = seconds.roundToInt()
            )
            sendAction(action)
        },
        valueRange = 1f..60f,
        interactionSource = thumbInteractionSource,
        thumb = {
            val sliderText = stringResource(
                id = ru.je_dog.core.feature.R.string.notification_settings_screen_location_update_seconds_interval_slider_label,
                locationUpdateSecondsInterval,
            )

            Label(
                label = {
                    PlainTooltip(
                        modifier = Modifier
                            .requiredSize(45.dp, 25.dp)
                            .wrapContentWidth(),
                        containerColor = MaterialTheme.colorScheme.primary,
                    ) {
                        Text(
                            text = sliderText,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                },
                interactionSource = thumbInteractionSource,
            ) {
                SliderDefaults.Thumb(
                    interactionSource = thumbInteractionSource,
                )
            }
        }
    )
}