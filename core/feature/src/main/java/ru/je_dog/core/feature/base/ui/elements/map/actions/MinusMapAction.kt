package ru.je_dog.core.feature.base.ui.elements.map.actions

import org.osmdroid.views.MapView
import ru.je_dog.core.feature.R

class MinusMapAction: MapAction {

    override val icon: Int = R.drawable.ic_minus

    override fun onAction(mapView: MapView) {
        mapView.controller.zoomOut()
    }
}