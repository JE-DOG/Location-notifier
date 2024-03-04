package ru.je_dog.core.feature.common.ui

import androidx.annotation.StringRes
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
fun SetGoalPointDialog(
    geoPoint: GeoPointPresentation,
    setGoalPointType: SetGoalPointType,
    onConfirm: (GeoPointPresentation) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(16.dp))
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            var inputMeters by remember {
                mutableStateOf(geoPoint.meters.toString())
            }
            var inputName by remember {
                mutableStateOf(geoPoint.name)
            }

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = inputName,
                onValueChange = { inputName = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = {
                    Text(text = stringResource(id = R.string.goal_location_name_title))
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
                    Text(text = stringResource(id = R.string.goal_location_meters_title))
                }
            )

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    onConfirm(
                        geoPoint.copy(
                            meters = inputMeters.toInt(),
                            name = inputName
                        )
                    )
                }
            ) {
                Text(text = stringResource(id = setGoalPointType.buttonText))
            }
        }
    }
}

sealed class SetGoalPointType(
    @StringRes
    val title: Int,
    @StringRes
    val buttonText: Int,
    @StringRes
    val observeKey: Int
){
    object Create: SetGoalPointType(
        R.string.create_goal_location_title,
        R.string.create,
        R.string.set_geo_point_create_observe_nav_key
    )
    data class Update(
        val geoPoint: GeoPointPresentation
    ): SetGoalPointType(
        R.string.update_goal_location_title,
        R.string.update,
        R.string.set_geo_point_update_observe_nav_key
    )
}