package ru.je_dog.core.feature.base.ui.elements

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView

@Composable
fun JMap(
    modifier: Modifier = Modifier,
    mapSettings: MapView.() -> Unit = {}
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            MapView(context).apply {
                setTileSource(TileSourceFactory.MAPNIK)
                setMultiTouchControls(true)
                minZoomLevel = 9.0
                maxZoomLevel = 20.0

                zoomController.setVisibility(
                    CustomZoomButtonsController.Visibility.NEVER
                )

                mapSettings(this)
                invalidate()
            }
        }
    )

}