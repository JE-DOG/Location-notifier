package ru.je_dog.feature.location_list.elements.ui.dialogs.geo_point.elements

import androidx.annotation.StringRes
import ru.je_dog.core.feature.R

enum class GeoPointDialogTitles(
    @StringRes
    val title: Int,
    @StringRes
    val onConfirmButtonText: Int
) {
    Create(
        title = R.string.geo_point_dialog_create_title,
        onConfirmButtonText = R.string.geo_point_dialog_create_on_confirm_button
    ),
    Update(
        title = R.string.geo_point_dialog_update_title,
        onConfirmButtonText = R.string.geo_point_dialog_update_on_confirm_button
    )
}