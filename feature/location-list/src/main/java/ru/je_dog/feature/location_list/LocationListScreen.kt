package ru.je_dog.feature.location_list

import android.content.Intent
import android.content.res.Resources
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.flow.onEach
import ru.je_dog.core.feature.base.ui.screen.EmptyListScreen
import ru.je_dog.core.feature.base.ui.screen.ErrorScreen
import ru.je_dog.core.feature.ext.observeResult
import ru.je_dog.core.feature.ext.removeResult
import ru.je_dog.core.feature.model.GeoPointPresentation
import ru.je_dog.feature.location_list.service.BroadcastLocationService
import ru.je_dog.feature.location_list.ui_elements.location_item.locationList
import ru.je_dog.feature.location_list.vm.LocationListAction
import ru.je_dog.feature.location_list.vm.LocationListViewModel
import java.lang.IllegalArgumentException
import kotlin.random.Random

@Composable
internal fun LocationListScreen(
    navController: NavController,
    viewModel: LocationListViewModel,
    navigateToSetGeoPoint: () -> Unit
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
                            onMoreClick = {
                                viewModel.action(it)
                            },
                            onItemClick = {
                                //todo Make it better
                                val intent = Intent(context,BroadcastLocationService::class.java).apply {
                                    val geoPointKey: String = context.getString(ru.je_dog.core.feature.R.string.goal_geo_point_extra_key)
                                    putExtra(geoPointKey,it)
                                }
                                context.startService(intent)
                            }
                        )

                    }

                }else {

                    EmptyListScreen(
                        ru.je_dog.core.feature.R.drawable.ic_add_location,
                        stringResource(id = ru.je_dog.core.feature.R.string.location_list_empty)
                    )

                }

                IconButton(
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 30.dp, end = 30.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = CircleShape
                        ),
                    onClick = navigateToSetGeoPoint
                ){
                    Icon(
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

        val action = LocationListAction.GetAllLocation
        viewModel.action(action)

        navController
            .observeResult(context.getString(ru.je_dog.core.feature.R.string.set_geo_point_observe_nav_key),null as GeoPointPresentation?)
            ?.collect { geoPoint ->
                if (geoPoint != null){
                    val intent = Intent(context,BroadcastLocationService::class.java).apply {
                        val geoPointKey = context.getString(ru.je_dog.core.feature.R.string.goal_geo_point_extra_key)
                        putExtra(geoPointKey,geoPoint)
                    }
                    val action = LocationListAction.AddLocation(geoPoint)
                    viewModel.action(action)

                    context.startService(intent)
                }
            }
    }
}