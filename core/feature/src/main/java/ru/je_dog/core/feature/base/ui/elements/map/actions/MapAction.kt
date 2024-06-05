package ru.je_dog.core.feature.base.ui.elements.map.actions

import androidx.annotation.DrawableRes
import org.osmdroid.views.MapView

interface MapAction {

    @get:DrawableRes
    val icon: Int

    fun onAction(mapView: MapView)

    companion object {

        fun getDefault(): List<MapAction> {
            return listOf(
                PlusMapAction(),
                MinusMapAction()
            )
        }

        fun getDefaultWith(vararg actions: MapAction): List<MapAction> {
            return listOf(
                PlusMapAction(),
                MinusMapAction()
            ) + actions
        }

    }
}

data class CustomMapAction(
    override val icon: Int,
    private val action: MapView.() -> Unit
): MapAction {
    override fun onAction(mapView: MapView) {
        action(mapView)
    }
}
