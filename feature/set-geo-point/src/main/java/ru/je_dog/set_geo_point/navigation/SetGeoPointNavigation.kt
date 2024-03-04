package ru.je_dog.set_geo_point.navigation

import android.content.Context
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.osmdroid.config.Configuration
import ru.je_dog.core.feature.app.tool_bar.ToolBar
import ru.je_dog.core.feature.app.tool_bar.ToolBarAction
import ru.je_dog.core.feature.app.tool_bar.ToolBarTitle
import ru.je_dog.core.feature.app.vm.LocalMainViewModel
import ru.je_dog.core.feature.common.ui.SetGoalPointType
import ru.je_dog.core.feature.model.GeoPointPresentation
import ru.je_dog.set_geo_point.SetGeoPointScreen

fun NavGraphBuilder.setGeoPoint(
    navController: NavController,
    navigateToBack: () -> Unit
){

    composable(
        "$SET_GEO_POINT_ROUTE?longitude={longitude}&latitude={latitude}&name={name}&meters={meters}&id={id}",
        arguments = listOf(
            navArgument("longitude"){
                defaultValue = 0f
                type = NavType.FloatType
            },
            navArgument("latitude"){
                defaultValue = 0f
                type = NavType.FloatType
            },
            navArgument("name"){
                nullable = true
                type = NavType.StringType
            },
            navArgument("meters"){
                defaultValue = 0
                type = NavType.IntType
            },
            navArgument("id"){
                defaultValue = 0
                type = NavType.IntType
            },
        )
    ){ backEntry ->
        val context = LocalContext.current
        val mainViewModel = LocalMainViewModel.current
        //Init osmdroid
        Configuration.getInstance().load(
            context,
            context.getSharedPreferences("OSM", Context.MODE_PRIVATE)
        )

        val geoPoint: GeoPointPresentation? = backEntry.getGeoPointPresentation()
        val setGoalPointType = if (geoPoint != null) SetGoalPointType.Update(geoPoint)
            else SetGoalPointType.Create

        SetGeoPointScreen(
            setGoalPointType = setGoalPointType,
            navController = navController,
            navigateToBack = navigateToBack
        )

        LaunchedEffect(key1 = Unit){
            mainViewModel.updateToolBar(
                ToolBar(
                    title = ToolBarTitle.Text(setGoalPointType.title),
                    startToolBarAction = ToolBarAction.back(navigateToBack),
                    endToolBarActions = null
                )
            )
        }
    }

}

fun NavController.navigateToSetGeoPoint(
    geoPoint: GeoPointPresentation? = null
){
    val route = geoPoint?.getRoute() ?: SET_GEO_POINT_ROUTE
    navigate(route)
}

const val SET_GEO_POINT_ROUTE = "SET_GEO_POINT_ROUTE"

private fun NavBackStackEntry.getGeoPointPresentation(): GeoPointPresentation? {
    val id = arguments?.getInt("id") ?: return null
    val name = arguments?.getString("name") ?: return null
    val longitude = arguments?.getFloat("longitude") ?: return null
    val latitude = arguments?.getFloat("latitude") ?: return null
    val meters = arguments?.getInt("meters") ?: return null
    return GeoPointPresentation(
        id,name,meters,longitude.toDouble(),latitude.toDouble()
    )
}

private fun GeoPointPresentation.getRoute(): String {
    val route = StringBuilder().apply {
        append("$SET_GEO_POINT_ROUTE?")
        appendData("longitude",longitude.toFloat())
        append("&")
        appendData("latitude",latitude.toFloat())
        append("&")
        appendData("name",name)
        append("&")
        appendData("meters",meters ?: 0)
        append("&")
        appendData("id",id ?: 0)
    }
    return route.toString()
}

private fun<T> StringBuilder.appendData(name: String,data: T){
    append("$name=$data")
}