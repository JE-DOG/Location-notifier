package ru.je_dog.core.feature.common.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.MapEventsOverlay

@Composable
fun Map(
    startPoint: GeoPoint = GeoPoint(42.0,49.0),
    mapEventsReceiver: ((MapView) -> MapEventsReceiver)? = null,
    initMap: (MapView) -> Unit = {}
) {
    AndroidView(
        factory = {
            MapView(it).apply {
                if (mapEventsReceiver != null) {
                    val overlayListener = MapEventsOverlay(
                        mapEventsReceiver(this)
                    )
                    overlays.add(overlayListener)
                }
                setTileSource(TileSourceFactory.MAPNIK)
                setMultiTouchControls(true)
                minZoomLevel = 9.0
                maxZoomLevel = 20.0
                controller.apply {
                    setZoom(8.0)
                    setCenter(startPoint)
                }
                zoomController.setVisibility(
                    CustomZoomButtonsController.Visibility.NEVER
                )
                invalidate()
                initMap(this)
            }
        }
    )
}