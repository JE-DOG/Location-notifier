package ru.je_dog.feature.location_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import kotlinx.coroutines.flow.filterIsInstance
import ru.je_dog.core.feature.R
import ru.je_dog.core.feature.model.BaseLocation
import ru.je_dog.core.feature.utills.ext.observeResult
import ru.je_dog.core.feature.model.GeoPointPresentation
import ru.je_dog.core.feature.utills.ext.removeResult
import ru.je_dog.feature.location_list.elements.callback.LocationListCallback
import ru.je_dog.feature.location_list.elements.ui.dialogs.geo_point.elements.GeoPointDialogStateSaver
import ru.je_dog.feature.location_list.elements.ui.dialogs.geo_point.elements.GeoPointDialogState
import ru.je_dog.feature.location_list.background_service.BroadcastLocationService
import ru.je_dog.feature.location_list.vm.LocationListAction
import ru.je_dog.feature.location_list.vm.LocationListViewModel

@Composable
internal fun LocationListScreen(
    navController: NavController,
    viewModel: LocationListViewModel,
    showStopBroadcastDialog: () -> Unit,
    navigateToSetGeoPointLocation: () -> Unit
) {
    val state = viewModel.state.collectAsState().value
    val context = LocalContext.current

    LocationListContent(
        state = state,
        callback = object: LocationListCallback {
            override fun getLocation(geoPointDialogState: GeoPointDialogState) {
                GeoPointDialogStateSaver.saveState(geoPointDialogState)
                navigateToSetGeoPointLocation()
            }

            override fun hideGeoPointDialog() {
                val action = LocationListAction.HideGeoPointDialog
                viewModel.action(action)

                GeoPointDialogStateSaver.saveState(null)
            }

            override fun onConfirmGeoPoint(geoPoint: GeoPointPresentation) {
                when(state.geoPointCreateDialog){
                    is GeoPointDialogState.Create -> {
                        val action = LocationListAction.AddLocation(
                            geoPoint
                        )

                        viewModel.action(action)
                    }

                    is GeoPointDialogState.Update -> {
                        val action = LocationListAction.UpdateLocation(
                            geoPoint
                        )

                        viewModel.action(action)
                    }

                    else -> {}
                }

                hideGeoPointDialog()
            }

            override fun updateGeoPoint(geoPoint: GeoPointPresentation) {
                val geoPointDialogState = GeoPointDialogState.Update(geoPoint)
                val action = LocationListAction.ShowGeoPointDialog(geoPointDialogState)

                viewModel.action(action)
            }

            override fun deleteGeoPoint(geoPoint: GeoPointPresentation) {
                val action = LocationListAction.DeleteLocation(geoPoint)
                viewModel.action(action)
            }

            override fun onItemClick(geoPoint: GeoPointPresentation) {
                //todo Make it better
                val isBroadcastStart = BroadcastLocationService.startBroadcast(
                    context,
                    geoPoint
                )
                if (!isBroadcastStart){
                    showStopBroadcastDialog()
                }
            }

            override fun onAddGeoPointClick() {
                val geoPointDialogState = GeoPointDialogState.Create()
                val action = LocationListAction.ShowGeoPointDialog(geoPointDialogState)

                viewModel.action(action)
            }

            override fun onTryAgainClick() {
                val action = LocationListAction.GetAllLocation

                viewModel.action(action)
            }
        },
    )

    LaunchedEffect(Unit){
        //Result geo point from set-geo-point screen
        val locationStateObserveKey = context.getString(R.string.set_geo_point_observe_nav_key)
        navController.observeResult(
            locationStateObserveKey,
            null as BaseLocation?
        )
            ?.filterIsInstance<BaseLocation>()
            ?.collect { location ->
                val action = LocationListAction.SetLocationForGeoPointDialog(location)

                viewModel.action(action)
                //Clean state
                navController.removeResult<BaseLocation?>(locationStateObserveKey)
            }
    }
}