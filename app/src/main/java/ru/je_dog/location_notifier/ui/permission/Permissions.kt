package ru.je_dog.location_notifier.ui.permission

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ru.je_dog.core.feature.R
import ru.je_dog.core.feature.base.ui.theme.LocationNotifierTheme
import ru.je_dog.location_notifier.ui.permission.vm.PermissionViewState

//region Preview
@Composable
@Preview(
    showBackground = true
)
private fun PermissionDialogPreview() {
    LocationNotifierTheme {
        Column {
            PermissionsDialog(
                state = PermissionViewState(
                    false,
                    false
                ),
                onPermissionResult = { permission, isGranted ->

                }
            )
        }
    }
}

@Composable
@Preview(
    showBackground = true
)
private fun PermissionItemPreview() {
    LocationNotifierTheme {
        Column {
            PermissionItem(
                modifier = Modifier
                    .padding(vertical = 8.dp),
                icon = R.drawable.ic_my_location,
                title = "Location",
                isGranted = false,
                permissionRequest = {}
            )

            Divider()

            PermissionItem(
                modifier = Modifier
                    .padding(vertical = 8.dp),
                icon = R.drawable.ic_notification,
                title = stringResource(id = R.string.permission_post_notification),
                isGranted = true,
                permissionRequest = {
                }
            )
        }
    }
}
//endregion

@Composable
fun PermissionsDialog(
    modifier: Modifier = Modifier,
    state: PermissionViewState,
    onPermissionResult: (String,Boolean) -> Unit,
) {
    val locationPermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            onPermissionResult(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                isGranted
            )
        }
    )
    val notificationPermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            onPermissionResult(
                Manifest.permission.POST_NOTIFICATIONS,
                isGranted
            )
        }
    )

    if (
        !state.locationIsGranted ||
        state.notificationIsGranted != true
    ){
        Dialog(onDismissRequest = {}) {
            Column(
                modifier = modifier
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    text = stringResource(id = ru.je_dog.core.feature.R.string.necessary_permissions),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground
                )
                
                Spacer(modifier = Modifier.height(24.dp))

                PermissionItem(
                    modifier = Modifier
                        .padding(vertical = 8.dp),
                    icon = R.drawable.ic_my_location,
                    title = stringResource(id = R.string.permission_location),
                    isGranted = state.locationIsGranted,
                    permissionRequest = {
                        locationPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    }
                )

                if (state.notificationIsGranted != null){
                    Divider()

                    PermissionItem(
                        modifier = Modifier
                            .padding(vertical = 8.dp),
                        icon = R.drawable.ic_notification,
                        title = stringResource(id = R.string.permission_post_notification),
                        isGranted = state.notificationIsGranted,
                        permissionRequest = {
                            notificationPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun PermissionItem(
    modifier: Modifier = Modifier,
    @DrawableRes
    icon: Int,
    title: String,
    isGranted: Boolean,
    permissionRequest: () -> Unit
) {
    Row(
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .clickable(onClick = permissionRequest),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(30.dp),
            painter = painterResource(id = icon),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.weight(1f))

        Checkbox(
            checked = isGranted,
            onCheckedChange = {},
        )
    }
}