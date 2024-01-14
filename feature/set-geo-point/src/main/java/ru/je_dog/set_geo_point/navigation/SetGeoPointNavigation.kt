package ru.je_dog.set_geo_point.navigation

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.osmdroid.config.Configuration
import ru.je_dog.set_geo_point.SetGeoPointScreen

fun NavGraphBuilder.setGeoPoint(
    navController: NavController
){

    composable(SET_GEO_POINT_ROUTE){

        val context = LocalContext.current

        Configuration.getInstance().load(
            context,
            context.getSharedPreferences("OSM", Context.MODE_PRIVATE)
        )

        SetGeoPointScreen(
            navController = navController
        )

    }

}

fun NavController.navigateToSetGeoPoint(){

    navigate(SET_GEO_POINT_ROUTE)

}

const val SET_GEO_POINT_ROUTE = "SET_GEO_POINT_ROUTE"