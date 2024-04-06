package ru.je_dog.feature.location_list.elements.ui.dialogs.geo_point.elements

object GeoPointDialogStateSaver {

    var dialogState: GeoPointDialogState? = null
        private set

    fun saveState(dialogState: GeoPointDialogState?){
        GeoPointDialogStateSaver.dialogState = dialogState
    }

}