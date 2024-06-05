package ru.je_dog.feature.notification_settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.je_dog.feature.notification_settings.content.NotificationContent
import ru.je_dog.feature.notification_settings.vm.NotificationSettingsViewModel

@Composable
fun NotificationScreen(
    viewModel: NotificationSettingsViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        NotificationContent(
            state = state,
            sendAction = viewModel::action
        )
    }
}