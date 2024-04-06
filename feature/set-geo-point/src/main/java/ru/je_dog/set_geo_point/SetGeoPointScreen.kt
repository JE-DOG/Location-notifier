package ru.je_dog.set_geo_point

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.Marker
import ru.je_dog.core.feature.R
import ru.je_dog.core.feature.base.ui.elements.JMap
import ru.je_dog.core.feature.model.BaseLocation
import ru.je_dog.core.feature.utills.ext.returnResult
import ru.je_dog.core.feature.model.GeoPointPresentation

@Composable
fun SetGeoPointScreen(
    navController: NavController
) {
    val context = LocalContext.current
    var selectGeoPoint by remember {
        mutableStateOf<BaseLocation?>(null)
    }

    Box {
        JMap(
            mapSettings = {
                val startPoint = GeoPoint(42.0, 49.0)
                val marker = Marker(this@JMap).apply {
                    icon = ActivityCompat.getDrawable(context, R.drawable.ic_add_location)
                }

                val listener = object : MapEventsReceiver {
                    override fun singleTapConfirmedHelper(touchGeo: GeoPoint?): Boolean {
                        selectGeoPoint = touchGeo?.run {
                            if (selectGeoPoint != null) {
                                selectGeoPoint!!.copy(
                                    longitude = longitude,
                                    latitude = latitude
                                )
                            } else {
                                BaseLocation(
                                    longitude = longitude,
                                    latitude = latitude
                                )
                            }
                        }

                        marker.position = touchGeo
                        controller.animateTo(touchGeo)
                        overlays.add(marker)
                        invalidate()

                        return true
                    }

                    override fun longPressHelper(p: GeoPoint?): Boolean {
                        return false
                    }
                }
                val overlayListener = MapEventsOverlay(listener)

                overlays.add(overlayListener)
                controller.apply {
                    setZoom(8.0)
                    setCenter(startPoint)
                }
                invalidate()
            }
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            enabled = selectGeoPoint != null,
            onClick = {
                val returnGeoPointKey = context.getString(R.string.set_geo_point_observe_nav_key)
                navController.returnResult(
                    returnGeoPointKey,
                    selectGeoPoint
                )

                navController.popBackStack()
            }
        ) {
            Text(text = stringResource(id = R.string.confirm))
        }
    }
}