package ru.je_dog.set_geo_point

import androidx.compose.animation.AnimatedVisibility
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
import org.osmdroid.views.overlay.Marker
import ru.je_dog.core.feature.R
import ru.je_dog.core.feature.common.ui.SetGoalPointDialog
import ru.je_dog.core.feature.common.ui.Map
import ru.je_dog.core.feature.common.ui.SetGoalPointType
import ru.je_dog.core.feature.ext.returnResult
import ru.je_dog.core.feature.model.GeoPointPresentation

@Composable
fun SetGeoPointScreen(
    setGoalPointType: SetGoalPointType,
    navController: NavController,
    navigateToBack: () -> Unit
) {
    val context = LocalContext.current
    var selectGeoPoint by remember {
        val geoPoint = if (setGoalPointType is SetGoalPointType.Update){
            setGoalPointType.geoPoint
        }else {
            null
        }
        mutableStateOf(geoPoint)
    }
    var showDialog by remember {
        mutableStateOf(false)
    }
    var marker: Marker? = null

    if (showDialog && selectGeoPoint != null){
        val returnGeoPointKey = stringResource(id = setGoalPointType.observeKey)

        SetGoalPointDialog(
            setGoalPointType = setGoalPointType,
            geoPoint = selectGeoPoint!!,
            onConfirm = { createdGeoPoint ->
                navController.returnResult(
                    returnGeoPointKey,
                    createdGeoPoint
                )
                navigateToBack()
            },
            onDismiss = { showDialog = false }
        )
    }

    Box {
        //todo startPoint by user location
        Map(
            mapEventsReceiver = { mapView ->

                object : MapEventsReceiver {
                    override fun singleTapConfirmedHelper(touchGeo: GeoPoint?): Boolean {
                        selectGeoPoint = touchGeo?.run {
                            GeoPointPresentation(
                                longitude = longitude,
                                latitude = latitude
                            )
                        }

                        with(mapView){
                            marker?.position = touchGeo
                            controller.animateTo(touchGeo)
                            overlays.add(marker)
                            invalidate()
                        }
                        return true
                    }


                    override fun longPressHelper(p: GeoPoint?): Boolean {
                        return false
                    }
                }
            },
            initMap = { mapView ->
                marker = Marker(mapView).apply {
                    icon = ActivityCompat.getDrawable(context, R.drawable.ic_add_location)
                    selectGeoPoint?.let { selectGeoPoint ->
                        position = selectGeoPoint.toGeoPoint()
                        mapView.controller.setCenter(selectGeoPoint.toGeoPoint())
                    }
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