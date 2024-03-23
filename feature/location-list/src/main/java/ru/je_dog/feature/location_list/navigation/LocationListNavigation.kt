package ru.je_dog.feature.location_list.navigation

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

fun NavGraphBuilder.locationList(
    appViewModel: AppViewModel,
    navController: NavController,
    navigateToSetGeoPoint: (GeoPointPresentation?) -> Unit,
    showStopBroadcastLocationDialog: () -> Unit
) {
    composable(LOCATION_LIST_ROUTE){
        val context = LocalContext.current
        val component = remember {
            DaggerLocationListComponent.factory()
                .create(LocationListComponentDepsStore.deps)
        }

        LocationListScreen(
            navController = navController,
            navigateToSetGeoPoint = navigateToSetGeoPoint,
            viewModel = component.locationListViewModel,
            showStopBroadcastDialog = showStopBroadcastLocationDialog
        )

        LaunchedEffect(Unit){
            val appToolBar = AppToolBar(
                title = context.getString(R.string.location_list_screen_title)
            )
            appViewModel.changeToolbar(appToolBar)
        }
    }
}

const val LOCATION_LIST_ROUTE = "LOCATION_LIST_ROUTE"