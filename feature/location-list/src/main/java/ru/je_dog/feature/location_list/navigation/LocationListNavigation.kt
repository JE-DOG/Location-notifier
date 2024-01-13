package ru.je_dog.feature.location_list.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.je_dog.feature.location_list.LocationListScreen
import ru.je_dog.feature.location_list.di.DaggerLocationListComponent
import ru.je_dog.feature.location_list.di.deps.LocationListComponentDepsStore

fun NavGraphBuilder.locationList(
    navController: NavController,
    navigateToSetGeoPoint: () -> Unit
) {

    composable(LOCATION_LIST_ROUTE){

        val component = DaggerLocationListComponent.factory()
            .create(LocationListComponentDepsStore.deps)

        LocationListScreen(
            navController = navController,
            navigateToSetGeoPoint = navigateToSetGeoPoint,
            viewModel = viewModel(factory = component.viewModelFactory)
        )

    }

}

const val LOCATION_LIST_ROUTE = "LOCATION_LIST_ROUTE"