package ru.je_dog.set_geo_point.navigation

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.osmdroid.config.Configuration
import ru.je_dog.core.feature.composition.LocalAppToolBarTitle
import ru.je_dog.core.feature.navigation.AppToolBar
import ru.je_dog.core.feature.navigation.AppToolBarItem
import ru.je_dog.set_geo_point.R
import ru.je_dog.set_geo_point.SetGeoPointScreen

fun NavGraphBuilder.setGeoPoint(
    navController: NavController,
    navigateBack: () -> Unit
){

    composable(SET_GEO_POINT_ROUTE){


        LocalAppToolBarTitle.current.value = AppToolBar(
            title = stringResource(id = ru.je_dog.core.feature.R.string.set_geo_point_screen_title),
            starItem = AppToolBarItem.back(navigateBack)
        )
        //Init osmdroid
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