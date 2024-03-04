package ru.je_dog.feature.location_list.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.je_dog.core.feature.R
import ru.je_dog.core.feature.app.tool_bar.ToolBar
import ru.je_dog.core.feature.app.tool_bar.ToolBarAction
import ru.je_dog.core.feature.app.tool_bar.ToolBarTitle
import ru.je_dog.core.feature.app.vm.LocalMainViewModel
import ru.je_dog.core.feature.model.GeoPointPresentation
import ru.je_dog.feature.location_list.LocationListScreen
import ru.je_dog.feature.location_list.di.DaggerLocationListComponent
import ru.je_dog.feature.location_list.di.deps.LocationListComponentDepsStore

fun NavGraphBuilder.locationList(
    navController: NavController,
    navigateToSetGeoPoint: (GeoPointPresentation?) -> Unit
) {

    composable(LOCATION_LIST_ROUTE){

        val component = DaggerLocationListComponent.factory()
            .create(LocationListComponentDepsStore.deps)
        val mainViewModel = LocalMainViewModel.current

        LocationListScreen(
            navController = navController,
            navigateToSetGeoPoint = navigateToSetGeoPoint,
            viewModel = viewModel(factory = component.viewModelFactory)
        )

        LaunchedEffect(key1 = Unit){
            mainViewModel.updateToolBar(
                ToolBar(
                    ToolBarTitle.Text(R.string.location_list_screen_title),
                    startToolBarAction = null,
                    endToolBarActions = listOf(ToolBarAction(R.drawable.ic_more_vertical,{}))
                )
            )
        }
    }

}

const val LOCATION_LIST_ROUTE = "LOCATION_LIST_ROUTE"