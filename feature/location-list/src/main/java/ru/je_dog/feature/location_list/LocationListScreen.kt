package ru.je_dog.feature.location_list

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.flow.filterIsInstance
import ru.je_dog.core.feature.R
import ru.je_dog.core.feature.base.app.tool_bar.AppToolBar
import ru.je_dog.core.feature.base.app.vm.AppViewModel
import ru.je_dog.core.feature.base.ui.screen.alert.EmptyListScreen
import ru.je_dog.core.feature.base.ui.screen.alert.ErrorScreen
import ru.je_dog.core.feature.utills.ext.observeResult
import ru.je_dog.core.feature.model.GeoPointPresentation
import ru.je_dog.feature.location_list.service.BroadcastLocationService
import ru.je_dog.feature.location_list.ui_elements.location_item.locationList
import ru.je_dog.feature.location_list.vm.LocationListAction
import ru.je_dog.feature.location_list.vm.LocationListViewModel

@Composable
internal fun LocationListScreen(
    navController: NavController,
    viewModel: LocationListViewModel,
    showStopBroadcastDialog: () -> Unit,
    navigateToSetGeoPoint: (GeoPointPresentation?) -> Unit
) {
    val state = viewModel.state.collectAsState().value
    val context = LocalContext.current

    Box {
        if (!state.isError){
            if (!state.isLoading){
                if (state.locations.isNotEmpty()){
                    LazyColumn(
                        Modifier
                            .fillMaxSize(),
                        contentPadding = PaddingValues(
                            horizontal = 16.dp,
                            vertical = 5.dp
                        )
                    ) {
                        locationList(
                            state.locations,
                            onUpdate = {
                                navigateToSetGeoPoint(it)
                            },
                            onDelete = {
                                val action = LocationListAction.DeleteLocation(it)
                                viewModel.action(action)
                            },
                            onItemClick = { geoPoint ->
                                //todo Make it better
                                val isBroadcastStart = BroadcastLocationService.startBroadcast(
                                    context,
                                    geoPoint
                                )
                                if (!isBroadcastStart){
                                    showStopBroadcastDialog()
                                }
                            }
                        )
                    }
                }else {
                    EmptyListScreen(
                        R.drawable.ic_add_location,
                        stringResource(id = R.string.location_list_empty)
                    )
                }

                IconButton(
                    modifier = Modifier
                        .size(75.dp)
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 15.dp, end = 15.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = CircleShape
                        ),
                    onClick = { navigateToSetGeoPoint(null) }
                ){
                    Icon(
                        modifier = Modifier
                            .size(28.dp),
                        painter = painterResource(ru.je_dog.core.feature.R.drawable.ic_add_location),
                        tint = Color.White,
                        contentDescription = "add location"
                    )
                }
            }else {
                CircularProgressIndicator(
                    Modifier.align(Alignment.Center)
                )
            }
        }else {
            Toast.makeText(LocalContext.current, ru.je_dog.core.feature.R.string.something_went_wrong, Toast.LENGTH_SHORT).show()
            ErrorScreen {
                val action = LocationListAction.GetAllLocation
                viewModel.action(action)
            }
        }
    }

    LaunchedEffect(key1 = Unit){
//        Observe result geo point from set-geo-point screen
        navController.observeResult(
            context.getString(ru.je_dog.core.feature.R.string.set_geo_point_observe_nav_key),
            null as GeoPointPresentation?
        )
            ?.filterIsInstance<GeoPointPresentation>()
            ?.collect { geoPoint ->
                Log.d("SomeTag",geoPoint.id.toString())
                if (geoPoint.id == null){
                    val isBroadcastStart = BroadcastLocationService.startBroadcast(
                        context,
                        geoPoint
                    )
                    if (!isBroadcastStart){
                        showStopBroadcastDialog()
                    }

                    val action = LocationListAction.AddLocation(geoPoint)
                    viewModel.action(action)
                }else {
                    val action = LocationListAction.UpdateLocation(geoPoint)
                    viewModel.action(action)
                }
            }

    }
}