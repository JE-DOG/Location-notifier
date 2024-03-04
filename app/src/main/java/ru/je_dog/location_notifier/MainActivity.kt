package ru.je_dog.location_notifier

import android.Manifest
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import ru.je_dog.core.feature.common.ui.theme.LocationNotifierTheme
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.je_dog.core.feature.app.vm.LocalMainViewModel
import ru.je_dog.core.feature.app.vm.MainViewModel
import ru.je_dog.core.feature.common.ui.TopAppBar
import ru.je_dog.feature.location_list.navigation.LOCATION_LIST_ROUTE
import ru.je_dog.feature.location_list.navigation.locationList
import ru.je_dog.set_geo_point.navigation.navigateToSetGeoPoint
import ru.je_dog.set_geo_point.navigation.setGeoPoint
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel
    private val appComponent = App.INSTANCE.appComponent

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.FOREGROUND_SERVICE,
                Manifest.permission.POST_NOTIFICATIONS,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
            ),
            0
        )

        setContent {
            LocationNotifierTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navHostController = rememberNavController()
                    val toolBar = mainViewModel.toolBar.collectAsState()

                    Scaffold(
                        topBar = {
                            TopAppBar(toolBar = toolBar.value)
                        }
                    ) { scaffoldPadding ->
                        CompositionLocalProvider(
                            LocalMainViewModel provides mainViewModel
                        ) {
                            NavHost(
                                modifier = Modifier
                                    .padding(scaffoldPadding),
                                navController = navHostController,
                                startDestination = LOCATION_LIST_ROUTE
                            ){
                                locationList(
                                    navController = navHostController,
                                    navigateToSetGeoPoint = navHostController::navigateToSetGeoPoint
                                )
                                setGeoPoint(
                                    navController = navHostController,
                                    navigateToBack = navHostController::popBackStack
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}