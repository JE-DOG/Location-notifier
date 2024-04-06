package ru.je_dog.core.feature.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BaseLocation(
    val longitude: Double,
    val latitude: Double,
): Parcelable
