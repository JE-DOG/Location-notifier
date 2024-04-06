package ru.je_dog.core.feature.utills.ext

import org.osmdroid.util.GeoPoint
import ru.je_dog.core.feature.model.GeoPointPresentation
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

infix fun GeoPointPresentation.lineMeters(point: GeoPointPresentation): Int {

    val lon1 = Math.toRadians(point.longitude)
    val lon2 = Math.toRadians(longitude)
    val lat1 = Math.toRadians(point.latitude)
    val lat2 = Math.toRadians(latitude)

    val dlon = lon2 - lon1
    val dlat = lat2 - lat1
    val a = (Math.pow(sin(dlat / 2), 2.0)
            + (cos(lat1) * cos(lat2)
            * Math.pow(sin(dlon / 2), 2.0)))
    val c = 2 * asin(sqrt(a))
    val r = 6371.0

    return (c * r * 1000).toInt()
}