package ru.je_dog.set_geo_point.navigation

import android.content.Context
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.osmdroid.config.Configuration
import ru.je_dog.core.feature.composition.LocalAppToolBarTitle
import ru.je_dog.core.feature.base.app.tool_bar.AppToolBar
import ru.je_dog.core.feature.base.app.tool_bar.AppToolBarItem
import ru.je_dog.core.feature.base.app.vm.AppViewModel
import ru.je_dog.set_geo_point.SetGeoPointScreen

fun NavGraphBuilder.setGeoPoint(
    appViewModel: AppViewModel,
    navController: NavController,
    navigateBack: () -> Unit
){
    composable(SET_GEO_POINT_ROUTE){
        //Init osmdroid
        val context = LocalContext.current
        Configuration.getInstance().load(
            context,
            context.getSharedPreferences("OSM", Context.MODE_PRIVATE)
        )

        SetGeoPointScreen(
            navController = navController
        )
        LaunchedEffect(Unit){
            val appToolBar = AppToolBar(
                title = context.getString(ru.je_dog.core.feature.R.string.set_geo_point_screen_title),
                starItem = AppToolBarItem.back(navigateBack)
            )
            appViewModel.changeToolbar(appToolBar)
        }
    }
}

fun NavController.navigateToSetGeoPoint() = navigate(SET_GEO_POINT_ROUTE)

const val SET_GEO_POINT_ROUTE = "SET_GEO_POINT_ROUTE"