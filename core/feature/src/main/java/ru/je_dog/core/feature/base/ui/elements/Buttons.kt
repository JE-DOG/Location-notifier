package ru.je_dog.core.feature.base.ui.elements

import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun OutlinedButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {

    androidx.compose.material3.OutlinedButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(text = text)
    }

}

@Composable
fun Button(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {

    androidx.compose.material3.Button(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(text = text)
    }

}

