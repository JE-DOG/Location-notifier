package ru.je_dog.set_geo_point

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.Marker
import ru.je_dog.core.feature.R
import ru.je_dog.core.feature.ext.returnResult
import ru.je_dog.core.feature.model.GeoPointPresentation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetGeoPointScreen(
    navController: NavController
) {

    val context = LocalContext.current
    var selectGeoPoint by remember {
        mutableStateOf(null as GeoPointPresentation?)
    }
    var showDialog by remember {
        mutableStateOf(false)
    }
    Log.d("GeoTag","GeoPoint: $selectGeoPoint")

    if (showDialog && selectGeoPoint != null){
        Dialog(
            onDismissRequest = { showDialog = false }
        ) {

            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .background(MaterialTheme.colorScheme.background, RoundedCornerShape(16.dp))
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {

                val returnGeoPointKey = stringResource(id = R.string.set_geo_point_observe_nav_key)
                var meters by remember {
                    mutableStateOf("")
                }

                Text(
                    text = stringResource(id = R.string.set_meters_goal_title)
                )

                Spacer(modifier = Modifier.height(5.dp))

                TextField(
                    value = meters,
                    onValueChange = { meters = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    onClick = {
                        navController.returnResult(
                            returnGeoPointKey,
                            selectGeoPoint!!.copy(
                                meters = meters.toInt()
                            )
                        )

                        navController.popBackStack()
                    }
                ) {

                }

            }

        }
    }

    Box {
        
        AndroidView(
            factory = {
                MapView(it).apply {

                    val marker = Marker(this).apply {
                        icon = ActivityCompat.getDrawable(context, R.drawable.ic_add_location)
                    }
                    val startPoint = GeoPoint(42.0,49.0)
                    val listener = object : MapEventsReceiver {

                        override fun singleTapConfirmedHelper(touchGeo: GeoPoint?): Boolean {
                            selectGeoPoint = touchGeo?.run {
                                GeoPointPresentation(
                                    longitude = longitude,
                                    latitude = latitude
                                )
                            }

                            marker.position = touchGeo
                            controller.setCenter(touchGeo)
                            overlays.add(marker)
                            invalidate()

                            return true
                        }

                        override fun longPressHelper(p: GeoPoint?): Boolean {
                            return false
                        }


                    }
                    val overlayListener = MapEventsOverlay(listener)

                    setTileSource(TileSourceFactory.MAPNIK)
                    setMultiTouchControls(true)
                    minZoomLevel = 9.0
                    maxZoomLevel = 20.0
                    overlays.add(overlayListener)

                    controller.apply {
                        setZoom(8.0)
                        setCenter(startPoint)
                    }
                    zoomController.setVisibility(
                        CustomZoomButtonsController.Visibility.NEVER
                    )


                    invalidate()

                }
            }
        )
        
        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            visible = selectGeoPoint != null
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    showDialog = true
                }
            ) {
                Text(
                    text = stringResource(id = R.string.confirm)
                )
            }
            
        }
        
    }
    

}