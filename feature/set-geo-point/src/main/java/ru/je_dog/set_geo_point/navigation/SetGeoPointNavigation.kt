package ru.je_dog.set_geo_point.navigation

import android.content.Context
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.osmdroid.config.Configuration
import ru.je_dog.core.feature.composition.LocalAppToolBarTitle
import ru.je_dog.core.feature.base.app.tool_bar.AppToolBar
import ru.je_dog.core.feature.base.app.tool_bar.AppToolBarItem
import ru.je_dog.core.feature.base.app.vm.AppViewModel
import ru.je_dog.core.feature.model.GeoPointPresentation
import ru.je_dog.set_geo_point.SetGeoPointScreen

fun NavGraphBuilder.setGeoPoint(
    appViewModel: AppViewModel,
    navController: NavController,
    navigateBack: () -> Unit
){
    composable(
        route = "$SET_GEO_POINT_ROUTE?" +
                "&isUpdate={isUpdate}" +
                "&latitude={latitude}" +
                "&longitude={longitude}" +
                "&name={name}" +
                "&meters={meters}" +
                "&geoPointId={geoPointId}",
        arguments = listOf(
            navArgument("isUpdate"){
                type = NavType.BoolType
                defaultValue = false
            },
            navArgument("latitude"){
                type = NavType.FloatType
                defaultValue = -1f
            },
            navArgument("longitude"){
                type = NavType.FloatType
                defaultValue = -1f
            },
            navArgument("name"){
                type = NavType.StringType
                nullable = true
            },
            navArgument("geoPointId"){
                type = NavType.IntType
                defaultValue = -1
            },
            navArgument("meters"){
                type = NavType.IntType
                defaultValue = -1
            }
        )
    ){ navBackEntry ->
        val context = LocalContext.current

        SetGeoPointScreen(
            navController = navController
        )

        LaunchedEffect(Unit){
            //change top bar
            val appToolBar = AppToolBar(
                title = context.getString(ru.je_dog.core.feature.R.string.set_geo_point_screen_title),
                starItem = AppToolBarItem.back(navigateBack)
            )
            appViewModel.changeToolbar(appToolBar)
            //Init osmdroid
            Configuration.getInstance().load(
                context,
                context.getSharedPreferences("OSM", Context.MODE_PRIVATE)
            )
        }
    }
}

fun NavController.navigateToSetGeoPoint() {
    navigate(SET_GEO_POINT_ROUTE)
}

const val SET_GEO_POINT_ROUTE = "SET_GEO_POINT_ROUTE"