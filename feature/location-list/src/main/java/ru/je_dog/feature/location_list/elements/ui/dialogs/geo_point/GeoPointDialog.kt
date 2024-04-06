package ru.je_dog.feature.location_list.elements.ui.dialogs.geo_point

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.je_dog.core.feature.R
import ru.je_dog.feature.location_list.elements.ui.dialogs.geo_point.elements.GeoPointDialogCallback
import ru.je_dog.feature.location_list.elements.ui.dialogs.geo_point.elements.GeoPointDialogState
import ru.je_dog.core.feature.base.ui.theme.LocationNotifierTheme
import ru.je_dog.core.feature.model.GeoPointPresentation
import ru.je_dog.core.feature.utills.ext.observeResult

//region Preview
@Composable
@Preview(showBackground = true)
private fun CreateGeoPointDialogPreview() {
    LocationNotifierTheme {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Content(
                state = GeoPointDialogState.Create(
                    geoPoint = null
                ),
                callback = GeoPointDialogCallback.Empty,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun UpdateGeoPointDialogPreview() {
    LocationNotifierTheme {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Content(
                state = GeoPointDialogState.Update(
                    geoPoint = GeoPointPresentation.mock()
                ),
                callback = GeoPointDialogCallback.Empty,
            )
        }
    }
}
//endregion

@Composable
internal fun GeoPointDialog(
    state: GeoPointDialogState?,
    callback: GeoPointDialogCallback,
) {
    if (state != null){
        Dialog(
            onDismissRequest = callback::hideGeoPointDialog
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background, RoundedCornerShape(16.dp))
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Content(
                    state = state,
                    callback = callback,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    state: GeoPointDialogState,
    callback: GeoPointDialogCallback,
) {
    val geoPoint = state.geoPoint
    val titles = state.titles
    var inputMeters by remember {
        val inputMeters = if (geoPoint?.meters != null && geoPoint.meters!! > 0){
            geoPoint.meters!!.toString()
        }else {
            ""
        }
        mutableStateOf(inputMeters)
    }
    var inputName by remember {
        val inputName = if (geoPoint?.name?.isNotEmpty() == true){
            geoPoint.name
        }else {
            ""
        }
        mutableStateOf(inputName)
    }
    val inputLocation by remember(geoPoint) {
        val location = if (geoPoint != null && geoPoint.longitude > 0 && geoPoint.latitude > 0){
            Pair(geoPoint.longitude, geoPoint.latitude)
        }else {
            null
        }
        mutableStateOf(location)
    }

    Text(
        text = stringResource(id = titles.title),
        style = MaterialTheme.typography.titleLarge
    )

    Spacer(modifier = Modifier.height(20.dp))

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

    Spacer(modifier = Modifier.height(5.dp))

    Box(
        modifier = Modifier

    ){
        val locationText = if (inputLocation != null){
            "${inputLocation?.first}, ${inputLocation?.second}"
        }else ""

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    state.geoPoint = GeoPointPresentation(
                        geoPoint?.id,
                        name = inputName,
                        meters = if (inputMeters.isNotEmpty()) inputMeters.toInt() else 0,
                        longitude = inputLocation?.first ?: 0.0,
                        latitude = inputLocation?.second ?: 0.0
                    )

                    callback.getLocation(state)
                },
            value = locationText,
            onValueChange = {},
            enabled = false,
            colors = TextFieldDefaults.textFieldColors(
                disabledTextColor = LocalContentColor.current.copy(LocalContentAlpha.current),
                disabledLabelColor =  MaterialTheme.colorScheme.onSurface.copy(ContentAlpha.medium)
            ),
            label = {
                Text(text = stringResource(id = R.string.set_goal_location_title))
            }
        )
    }

    Spacer(modifier = Modifier.height(15.dp))

    Button(
        modifier = Modifier
            .fillMaxWidth(),
        enabled = inputMeters.isNotEmpty() &&
            inputLocation != null &&
            inputName.isNotEmpty(),
        onClick = {
            val createdGeoPoint = GeoPointPresentation(
                geoPoint?.id,
                name = inputName,
                meters = inputMeters.toInt(),
                longitude = inputLocation!!.first,
                latitude = inputLocation!!.second
            )

            callback.onConfirmGeoPoint(createdGeoPoint)
        }
    ) {
        Text(text = stringResource(id = titles.onConfirmButtonText))
    }
}