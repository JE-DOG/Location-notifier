package ru.je_dog.core.feature.base.ui.elements

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.je_dog.core.feature.R

@Composable
fun StopBroadcastLocationDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit
) {

    AlertDialog(
        title = {
            Text(text = stringResource(id = R.string.broadcast_location_is_active_title))
        },
        text = {
            Text(text = stringResource(id = R.string.stop_broadcast_location_message))
        },
        onDismissRequest = onDismissRequest,
        dismissButton = {
            OutlinedButton(
                text = stringResource(id = R.string.dismiss),
                onClick = onDismissRequest
            )
        },
        confirmButton = {
            Button(
                text = stringResource(id = R.string.confirm),
                onClick = onConfirm
            )
        }
    )

}