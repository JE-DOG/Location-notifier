package ru.je_dog.feature.location_list.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.je_dog.core.feature.R
import ru.je_dog.core.feature.base.app.tool_bar.AppToolBar
import ru.je_dog.core.feature.base.app.tool_bar.AppToolBarItem
import ru.je_dog.feature.location_list.LocationListScreen
import ru.je_dog.feature.location_list.di.DaggerLocationListComponent
import ru.je_dog.feature.location_list.di.deps.LocationListComponentDepsStore

fun NavGraphBuilder.locationList(
    navController: NavController,
    changeToolBar: (AppToolBar) -> Unit,
    showStopBroadcastLocationDialog: () -> Unit,
    navigateToSetGeoPointLocation: () -> Unit,
    navigateToNotificationSettings: () -> Unit,
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

        LaunchedEffect(Unit){
            val settingsItem = AppToolBarItem(
                icon = R.drawable.ic_setting,
                action = navigateToNotificationSettings
            )
            val appToolBar = AppToolBar(
                title = context.getString(R.string.location_list_screen_title),
                endItems = listOf(
                    settingsItem
                )
            )
            changeToolBar(appToolBar)
        }
    }
}

const val LOCATION_LIST_ROUTE = "LOCATION_LIST_ROUTE"