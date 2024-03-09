package ru.je_dog.core.feature.utills

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun getShapeByIndex(
    index: Int,
    size: Int,
    corner: Dp = 16.dp
) = when {

    size == 1 -> RoundedCornerShape(corner)

    index == 0 -> RoundedCornerShape(topStart = corner,topEnd = corner)

    index == size - 1 -> RoundedCornerShape(bottomStart = corner, bottomEnd = corner)

    else -> RoundedCornerShape(0)

}