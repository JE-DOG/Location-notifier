package ru.je_dog.feature.location_list.elements.ui.location_list

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import ru.je_dog.core.feature.utills.getShapeByIndex
import ru.je_dog.core.feature.model.GeoPointPresentation

internal fun LazyListScope.locationList(
    geoPointList: List<GeoPointPresentation>,
    onUpdate: (GeoPointPresentation) -> Unit,
    onDelete: (GeoPointPresentation) -> Unit,
    onItemClick: (GeoPointPresentation) -> Unit
) {
    itemsIndexed(
        geoPointList,
    ){ index,geoPoint ->
        LocationItem(
            geoPoint,
            getShapeByIndex(index,geoPointList.size),
            onDelete = onDelete,
            onUpdate = onUpdate,
            onItemClick = onItemClick
        )
    }
}