package ru.je_dog.set_geo_point

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityCompat
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.events.MapListener
import org.osmdroid.events.ScrollEvent
import org.osmdroid.events.ZoomEvent
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.Marker
import ru.je_dog.core.feature.R
import ru.je_dog.core.feature.base.ui.elements.map.JMap
import ru.je_dog.core.feature.base.ui.elements.map.actions.CustomMapAction
import ru.je_dog.core.feature.base.ui.elements.map.actions.MapAction
import ru.je_dog.core.feature.model.BaseLocation
import ru.je_dog.set_geo_point.vm.SetGeoPointViewState
import ru.je_dog.set_geo_point.vm.components.SetGeoPointAction

@Composable
fun SetGeoPointContent(
    state: SetGeoPointViewState,
    sendAction: (SetGeoPointAction.UI) -> Unit,
) {
    val context = LocalContext.current

    val mapActions = remember(state.canGetLocation) {
        val icon = if (state.canGetLocation){
            R.drawable.ic_my_location
        }else {
            R.drawable.ic_my_location_off
        }

        val setUserLocationMapAction = CustomMapAction(
            icon = icon,
            action = {
                if (state.canGetLocation){
                    val action = SetGeoPointAction.UI.ShowUserLocation
                    sendAction(action)
                }
            }
        )

        MapAction.getDefaultWith(setUserLocationMapAction)
    }

    Box {
        JMap(
            mapSettings = {
                val startPoint = GeoPoint(42.0,49.0)
                val marker = Marker(this@JMap).apply {
                    icon = ActivityCompat.getDrawable(context, R.drawable.ic_add_location)
                }

                val mapClickListener = object: MapEventsReceiver {
                    override fun singleTapConfirmedHelper(touchGeo: GeoPoint): Boolean {
                        val touchLocation = BaseLocation(
                            longitude = touchGeo.longitude,
                            latitude = touchGeo.latitude
                        )
                        val action = SetGeoPointAction.UI.ChangeGoalLocation(touchLocation)
                        sendAction(action)

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
                val overlayListener = MapEventsOverlay(mapClickListener)
                overlays.add(overlayListener)

                controller.apply {
                    setZoom(9.0)
                    setCenter(startPoint)
                }
                invalidate()
            },
            mapActions = mapActions,
            onUpdate = {
                state.userLocation?.let {
                    controller.apply {
                        zoomTo(18.0)
                        animateTo(state.userLocation)
                    }

                    invalidate()
                }
            },
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            enabled = state.selectedLocation != null,
            onClick = {
                val action = SetGeoPointAction.UI.OnConfirm
                sendAction(action)
            }
        ) {
            Text(text = stringResource(id = R.string.confirm))
        }
    }
}