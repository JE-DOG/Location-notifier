package ru.je_dog.feature.location_list.ui_elements.location_item

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import ru.je_dog.core.feature.ext.getShapeByIndex
import ru.je_dog.core.feature.model.GeoPointPresentation
import ru.je_dog.feature.location_list.vm.LocationListAction

internal fun LazyListScope.locationList(
    geoPointList: List<GeoPointPresentation>,
    onMoreClick: (LocationListAction) -> Unit,
    onItemClick: (GeoPointPresentation) -> Unit
) {

    itemsIndexed(
        geoPointList
    ){ index,geoPoint ->

        LocationItem(
            geoPoint,
            getShapeByIndex(index,geoPointList.size),
            onMoreClick = onMoreClick,
            onItemClick = onItemClick
        )

    }

}