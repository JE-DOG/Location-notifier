package ru.je_dog.feature.location_list.ui_elements.location_item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jetbrains.annotations.VisibleForTesting
import ru.je_dog.core.feature.ext.getShapeByIndex
import ru.je_dog.core.feature.model.GeoPointPresentation

@Composable
fun LocationItem(
    geoPoint: GeoPointPresentation,
    shape: Shape = RoundedCornerShape(0),
    onMoreClick: (GeoPointPresentation) -> Unit,
    onItemClick: (GeoPointPresentation) -> Unit
) {

    Row(
        Modifier
            .clickable { onItemClick(geoPoint) }
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 1.dp)
            .background(Color.Gray,shape)
            .padding(horizontal = 16.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        LocationInfo(geoPoint)

        Spacer(Modifier.weight(1f))

        IconButton(
            onClick = {
                onMoreClick(geoPoint)
            }
        ){
            Icon(
                painter = painterResource(ru.je_dog.core.feature.R.drawable.ic_more_vertical),
                contentDescription = "more",
                tint = Color.LightGray
            )
        }

    }

}

@Composable
private fun LocationInfo(
    geoPoint: GeoPointPresentation
) {

    Column(
        Modifier
            .wrapContentSize()
    ) {

        Text(
            text = geoPoint.name,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.background
        )

    }

}

@Composable
@VisibleForTesting
@Preview(
    showBackground = true,
    showSystemUi = true
)
internal fun LocationListPreview(){

    val list = GeoPointPresentation.mockList(50)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = 20.dp,
            vertical = 10.dp
        )
    ) {

        locationList(
            list,
            onMoreClick = {},
            onItemClick = {}
        )

    }

}