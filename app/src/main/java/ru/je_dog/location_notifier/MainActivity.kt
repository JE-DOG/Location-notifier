package ru.je_dog.location_notifier

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import ru.je_dog.core.feature.base.ui.theme.LocationNotifierTheme
import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.je_dog.core.feature.composition.LocalAppToolBarTitle
import ru.je_dog.core.feature.ext.observeResult
import ru.je_dog.feature.location_list.navigation.LOCATION_LIST_ROUTE
import ru.je_dog.feature.location_list.navigation.locationList
import ru.je_dog.set_geo_point.navigation.navigateToSetGeoPoint
import ru.je_dog.set_geo_point.navigation.setGeoPoint

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                    color = MaterialTheme.colorScheme.background,

                ) {


                    val navHostController = rememberNavController()
                    val title = remember {
                        mutableStateOf("")
                    }

                    Scaffold(
                        topBar = {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp)
                                    .background(MaterialTheme.colorScheme.primary)
                            ) {

                                Text(
                                    modifier = Modifier
                                        .align(Alignment.Center),
                                    text = title.value,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.background
                                )

                            }
                        }
                    ) { scaffoldPadding ->

                        CompositionLocalProvider(
                            LocalAppToolBarTitle provides title
                        ) {
                            NavHost(
                                modifier = Modifier
                                    .padding(scaffoldPadding),
                                navController = navHostController,
                                startDestination = LOCATION_LIST_ROUTE
                            ) {

                                locationList(
                                    navController = navHostController,
                                    navigateToSetGeoPoint = navHostController::navigateToSetGeoPoint,
                                )
                                setGeoPoint(
                                    navController = navHostController,
                                )

                            }
                        }

                    }

                }
            }
        }
    }
}