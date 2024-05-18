package ru.je_dog.location_notifier.ui

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.je_dog.core.feature.base.app.tool_bar.AppToolBar
import ru.je_dog.core.feature.base.app.vm.AppViewState
import ru.je_dog.core.feature.base.ui.elements.StopBroadcastLocationDialog
import ru.je_dog.core.feature.base.ui.elements.TopAppToolBar
import ru.je_dog.feature.location_list.navigation.LOCATION_LIST_ROUTE
import ru.je_dog.feature.location_list.navigation.locationList
import ru.je_dog.feature.location_list.background_service.BroadcastLocationService
import ru.je_dog.location_notifier.ui.permission.PermissionsDialog
import ru.je_dog.location_notifier.ui.permission.vm.PermissionViewState
import ru.je_dog.set_geo_point.navigation.navigateToSetGeoPoint
import ru.je_dog.set_geo_point.navigation.setGeoPoint

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppUi(
    appState: AppViewState,
    permissionState: PermissionViewState,
    changeToolBar: (AppToolBar) -> Unit,
    onPermissionResult: (String,Boolean) -> Unit,
) {
    val activity = LocalContext.current as ComponentActivity

    val navHostController = rememberNavController()
    var showStopBroadcastLocationDialog by remember {
        mutableStateOf(false)
    }

    PermissionsDialog(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = MaterialTheme.shapes.large
            )
            .padding(16.dp),
        state = permissionState,
        onPermissionResult = onPermissionResult
    )

    if (showStopBroadcastLocationDialog) {
        StopBroadcastLocationDialog(
            onDismissRequest = { showStopBroadcastLocationDialog = false },
            onConfirm = {
                val myService =
                    Intent(activity, BroadcastLocationService::class.java)
                activity.stopService(myService)

                showStopBroadcastLocationDialog = false
            }
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                TopAppToolBar(appToolBar = appState.toolBar)
            }
        ) { scaffoldPadding ->

            NavHost(
                modifier = Modifier
                    .padding(scaffoldPadding),
                navController = navHostController,
                startDestination = LOCATION_LIST_ROUTE
            ) {
                locationList(
                    changeToolBar = changeToolBar,
                    navController = navHostController,
                    showStopBroadcastLocationDialog = {
                        showStopBroadcastLocationDialog = true
                    },
                    navigateToSetGeoPointLocation = navHostController::navigateToSetGeoPoint
                )
                setGeoPoint(
                    changeToolbar = changeToolBar,
                    navController = navHostController,
                    navigateBack = { navHostController.popBackStack() },
                )
            }
        }
    }
}