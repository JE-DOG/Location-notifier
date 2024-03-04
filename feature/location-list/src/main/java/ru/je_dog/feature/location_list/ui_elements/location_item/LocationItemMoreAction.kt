package ru.je_dog.feature.location_list.ui_elements.location_item

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import ru.je_dog.core.feature.model.GeoPointPresentation

@Immutable
sealed class LocationItemMoreAction(
    @StringRes
    val text: Int,
    val geoPoint: GeoPointPresentation
) {

    class Update(
        geoPoint: GeoPointPresentation
    ): LocationItemMoreAction(ru.je_dog.core.feature.R.string.update,geoPoint)

    class Delete(
        geoPoint: GeoPointPresentation
    ): LocationItemMoreAction(ru.je_dog.core.feature.R.string.delete,geoPoint)

}