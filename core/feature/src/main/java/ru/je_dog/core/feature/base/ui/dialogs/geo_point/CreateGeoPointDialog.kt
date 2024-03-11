package ru.je_dog.core.feature.base.ui.dialogs.geo_point

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ru.je_dog.core.feature.R
import ru.je_dog.core.feature.model.GeoPointPresentation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGeoPointDialog(
    geoPoint: GeoPointPresentation,
    onConfirm: (GeoPointPresentation) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(16.dp))
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            var inputMeters by remember {
                mutableStateOf("")
            }
            var inputName by remember {
                mutableStateOf("")
            }

            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = inputName,
                onValueChange = { inputName = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = {
                    Text(text = stringResource(id = R.string.set_goal_name_title))
                }
            )

            Spacer(modifier = Modifier.height(5.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = inputMeters,
                onValueChange = { inputMeters = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = {
                    Text(text = stringResource(id = R.string.set_goal_meters_title))
                }
            )

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    val createdGeoPoint = geoPoint.copy(
                        meters = inputMeters.toInt(),
                        name = inputName
                    )
                    onConfirm(createdGeoPoint)
                }
            ) {
                Text(text = stringResource(id = R.string.begin))
            }
        }
    }
}