package ru.je_dog.location_notifier

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.je_dog.core.feature.base.ui.elements.StopBroadcastLocationDialog
import ru.je_dog.core.feature.base.ui.elements.TopAppToolBar
import ru.je_dog.core.feature.base.ui.theme.LocationNotifierTheme
import ru.je_dog.core.feature.base.app.vm.AppViewModel
import ru.je_dog.feature.location_list.navigation.LOCATION_LIST_ROUTE
import ru.je_dog.feature.location_list.navigation.locationList
import ru.je_dog.feature.location_list.service.BroadcastLocationService
import ru.je_dog.set_geo_point.navigation.navigateToSetGeoPoint
import ru.je_dog.set_geo_point.navigation.setGeoPoint
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appViewModel: AppViewModel
    private val appComponent = App.INSTANCE.appComponent

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        //todo make better
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.FOREGROUND_SERVICE,
                Manifest.permission.FOREGROUND_SERVICE_LOCATION,
                Manifest.permission.POST_NOTIFICATIONS,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
            ),
            0
        )

        setContent {
            val appState = appViewModel.state.collectAsState()
            val navHostController = rememberNavController()
            var showStopBroadcastLocationDialog by remember {
                mutableStateOf(false)
            }

            if (showStopBroadcastLocationDialog) {
                StopBroadcastLocationDialog(
                    onDismissRequest = { showStopBroadcastLocationDialog = false },
                    onConfirm = {
                        val myService =
                            Intent(this@MainActivity, BroadcastLocationService::class.java)
                        stopService(myService)

                        showStopBroadcastLocationDialog = false
                    }
                )
            }

            LocationNotifierTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppToolBar(appToolBar = appState.value.toolBar)
                        }
                    ) { scaffoldPadding ->

                        NavHost(
                            modifier = Modifier
                                .padding(scaffoldPadding),
                            navController = navHostController,
                            startDestination = LOCATION_LIST_ROUTE
                        ) {
                            locationList(
                                appViewModel = appViewModel,
                                navController = navHostController,
                                navigateToSetGeoPoint = navHostController::navigateToSetGeoPoint,
                                showStopBroadcastLocationDialog = {
                                    showStopBroadcastLocationDialog = true
                                },
                            )
                            setGeoPoint(
                                appViewModel = appViewModel,
                                navController = navHostController,
                                navigateBack = { navHostController.popBackStack() },
                            )
                        }
                    }
                }
            }
        }
    }
}