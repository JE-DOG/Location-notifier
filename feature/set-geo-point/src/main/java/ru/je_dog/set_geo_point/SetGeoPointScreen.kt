package ru.je_dog.set_geo_point

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import ru.je_dog.core.feature.R
import ru.je_dog.core.feature.utills.CollectStateFlow
import ru.je_dog.core.feature.utills.ext.returnResult
import ru.je_dog.set_geo_point.vm.SetGeoPointViewModel
import ru.je_dog.set_geo_point.vm.components.SetGeoPointEffect

@Composable
fun SetGeoPointScreen(
    navController: NavController,
    viewModel: SetGeoPointViewModel,
) {
    val context = LocalContext.current

    val state by viewModel.state.collectAsState()

    SetGeoPointContent(
        state = state,
        sendAction = viewModel::action
    )

    CollectStateFlow(viewModel.effect) { effect ->
        when(effect){

            is SetGeoPointEffect.ReturnLocation -> {
                val returnGeoPointKey = context.getString(R.string.set_geo_point_observe_nav_key)
                navController.returnResult(
                    returnGeoPointKey,
                    state.selectedLocation
                )

                navController.popBackStack()
            }

            null -> {}
        }
    }
}