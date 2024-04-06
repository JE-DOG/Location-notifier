package ru.je_dog.feature.location_list.elements.ui.dialogs.geo_point.elements

import ru.je_dog.core.feature.model.GeoPointPresentation

sealed interface GeoPointDialogState {
    var geoPoint: GeoPointPresentation?
    val titles: GeoPointDialogTitles

    data class Create(
        override var geoPoint: GeoPointPresentation? = null,
        override val titles: GeoPointDialogTitles = GeoPointDialogTitles.Create,
    ): GeoPointDialogState

    data class Update(
        override var geoPoint: GeoPointPresentation?,
        override val titles: GeoPointDialogTitles = GeoPointDialogTitles.Update,
    ): GeoPointDialogState

}
