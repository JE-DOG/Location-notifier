package ru.je_dog.feature.location_list.navigation

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.je_dog.core.feature.R
import ru.je_dog.core.feature.composition.LocalAppToolBarTitle
import ru.je_dog.core.feature.navigation.AppToolBar
import ru.je_dog.feature.location_list.LocationListScreen
import ru.je_dog.feature.location_list.di.DaggerLocationListComponent
import ru.je_dog.feature.location_list.di.deps.LocationListComponentDepsStore

fun NavGraphBuilder.locationList(
    navController: NavController,
    navigateToSetGeoPoint: () -> Unit,
    showStopBroadcastLocationDialog: () -> Unit
) {

    composable(LOCATION_LIST_ROUTE){

        LocalAppToolBarTitle.current.value = AppToolBar(
            title = stringResource(id = R.string.location_list_screen_title)
        )

        val component = DaggerLocationListComponent.factory()
            .create(LocationListComponentDepsStore.deps)

        LocationListScreen(
            navController = navController,
            navigateToSetGeoPoint = navigateToSetGeoPoint,
            viewModel = viewModel(factory = component.viewModelFactory),
            showStopBroadcastDialog = showStopBroadcastLocationDialog
        )

    }

}

const val LOCATION_LIST_ROUTE = "LOCATION_LIST_ROUTE"