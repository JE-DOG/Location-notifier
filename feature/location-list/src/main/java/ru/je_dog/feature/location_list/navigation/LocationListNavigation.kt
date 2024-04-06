package ru.je_dog.feature.location_list.navigation

import android.util.Log
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.je_dog.core.feature.R
import ru.je_dog.core.feature.composition.LocalAppToolBarTitle
import ru.je_dog.core.feature.base.app.tool_bar.AppToolBar
import ru.je_dog.core.feature.base.app.vm.AppViewModel
import ru.je_dog.core.feature.model.GeoPointPresentation
import ru.je_dog.feature.location_list.LocationListScreen
import ru.je_dog.feature.location_list.di.DaggerLocationListComponent
import ru.je_dog.feature.location_list.di.deps.LocationListComponentDepsStore
import ru.je_dog.feature.location_list.elements.ui.dialogs.geo_point.elements.GeoPointDialogState

fun NavGraphBuilder.locationList(
    appViewModel: AppViewModel,
    navController: NavController,
    showStopBroadcastLocationDialog: () -> Unit,
    navigateToSetGeoPointLocation: () -> Unit,
) {
    composable(LOCATION_LIST_ROUTE){
        val context = LocalContext.current
        val component = remember {
            DaggerLocationListComponent.factory()
                .create(LocationListComponentDepsStore.deps)
        }

        LocationListScreen(
            navController = navController,
            viewModel = component.locationListViewModel,
            showStopBroadcastDialog = showStopBroadcastLocationDialog,
            navigateToSetGeoPointLocation = navigateToSetGeoPointLocation,
        )

        DisposableEffect(key1 = Unit){
            onDispose {
                Log.d("HelloTag","Some")
            }
        }
        LaunchedEffect(Unit){
            val appToolBar = AppToolBar(
                title = context.getString(R.string.location_list_screen_title)
            )
            appViewModel.changeToolbar(appToolBar)
        }
    }
}

const val LOCATION_LIST_ROUTE = "LOCATION_LIST_ROUTE"