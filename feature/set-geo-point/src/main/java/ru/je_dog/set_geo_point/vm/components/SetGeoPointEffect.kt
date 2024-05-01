package ru.je_dog.set_geo_point.vm.components


sealed interface SetGeoPointEffect {

    object ReturnLocation: SetGeoPointEffect

}