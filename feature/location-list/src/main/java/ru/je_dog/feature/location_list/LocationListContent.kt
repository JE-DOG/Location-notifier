package ru.je_dog.feature.location_list

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import ru.je_dog.core.feature.R
import ru.je_dog.feature.location_list.elements.ui.dialogs.geo_point.GeoPointDialog
import ru.je_dog.feature.location_list.elements.ui.dialogs.geo_point.elements.GeoPointDialogState
import ru.je_dog.core.feature.base.ui.screen.alert.EmptyListScreen
import ru.je_dog.core.feature.base.ui.screen.alert.ErrorScreen
import ru.je_dog.core.feature.base.ui.theme.LocationNotifierTheme
import ru.je_dog.core.feature.model.GeoPointPresentation
import ru.je_dog.feature.location_list.elements.callback.EmptyLocationListCallback
import ru.je_dog.feature.location_list.elements.callback.LocationListCallback
import ru.je_dog.feature.location_list.elements.ui.location_list.locationList
import ru.je_dog.feature.location_list.elements.state.LocationListViewState

//region Preview
@Composable
@Preview(
    showSystemUi = true
)
private fun LocationListContentPreview() {
    LocationNotifierTheme {
        LocationListContent(
            state = LocationListViewState(
                locations = GeoPointPresentation.mockList(10)
            ),
            callback = EmptyLocationListCallback,
        )
    }
}

@Composable
@Preview(
    showSystemUi = true
)
private fun LocationListContentEmptyPreview() {
    LocationNotifierTheme {
        LocationListContent(
            state = LocationListViewState(),
            callback = EmptyLocationListCallback,
        )
    }
}

@Composable
@Preview(
    showSystemUi = true
)
private fun LocationListContentErrorPreview() {
    LocationNotifierTheme {
        LocationListContent(
            state = LocationListViewState(
                isError = true
            ),
            callback = EmptyLocationListCallback,
        )
    }
}

@Composable
@Preview(
    showSystemUi = true
)
private fun LocationListContentLoadingPreview() {
    LocationNotifierTheme {
        LocationListContent(
            modifier = Modifier.fillMaxSize(),
            state = LocationListViewState(
                isLoading = true
            ),
            callback = EmptyLocationListCallback,
        )
    }
}

@Composable
@Preview(
    showSystemUi = true
)
private fun LocationListContentCreateGeoPointPreview() {
    LocationNotifierTheme {
        LocationListContent(
            modifier = Modifier.fillMaxSize(),
            state = LocationListViewState(
                geoPointCreateDialog = GeoPointDialogState.Create()
            ),
            callback = EmptyLocationListCallback,
        )
    }
}

@Composable
@Preview(
    showSystemUi = true
)
private fun LocationListContentUpdateGeoPointPreview() {
    LocationNotifierTheme {
        LocationListContent(
            modifier = Modifier.fillMaxSize(),
            state = LocationListViewState(
                geoPointCreateDialog = GeoPointDialogState.Update(
                    GeoPointPresentation.mock()
                )
            ),
            callback = EmptyLocationListCallback,
        )
    }
}
//endregion

@Composable
internal fun LocationListContent(
    modifier: Modifier = Modifier,
    state: LocationListViewState,
    callback: LocationListCallback,
) {
    GeoPointDialog(
        state = state.geoPointCreateDialog,
        callback = callback,
    )

    Box(
        modifier = modifier
    ) {
        if (!state.isError){
            if (!state.isLoading){
                if (state.locations.isNotEmpty()){
                    LazyColumn(
                        Modifier
                            .fillMaxSize(),
                        contentPadding = PaddingValues(
                            horizontal = 16.dp,
                            vertical = 5.dp
                        )
                    ) {
                        locationList(
                            state.locations,
                            onUpdate = callback::updateGeoPoint,
                            onDelete = callback::deleteGeoPoint,
                            onItemClick = callback::onItemClick
                        )
                    }
                }else {
                    EmptyListScreen(
                        R.drawable.ic_add_location,
                        stringResource(id = R.string.location_list_empty)
                    )
                }

                IconButton(
                    modifier = Modifier
                        .size(75.dp)
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 15.dp, end = 15.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = CircleShape
                        ),
                    onClick = callback::onAddGeoPointClick
                ){
                    Icon(
                        modifier = Modifier
                            .size(28.dp),
                        painter = painterResource(R.drawable.ic_add_location),
                        tint = Color.White,
                        contentDescription = "add location"
                    )
                }
            }else {
                CircularProgressIndicator(
                    Modifier
                        .align(Alignment.Center)
                )
            }
        }else {
            ErrorScreen(
                onRetryClick = callback::onTryAgainClick
            )
        }
    }
}