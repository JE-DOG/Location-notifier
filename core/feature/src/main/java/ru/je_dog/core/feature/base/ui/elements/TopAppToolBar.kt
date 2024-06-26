package ru.je_dog.core.feature.base.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.je_dog.core.feature.base.app.tool_bar.AppToolBar

@Composable
fun TopAppToolBar(
    appToolBar: AppToolBar
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = 5.dp)
    ) {

        appToolBar.starItem?.let { toolBarItem ->
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterStart),
                onClick = toolBarItem.action
            ) {
                Icon(
                    painter = painterResource(id = toolBarItem.icon),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.background,
                )
            }
        }

        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = appToolBar.title,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.background
        )

        appToolBar.endItems?.let { toolBarItemList ->
            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            ) {
                toolBarItemList.forEach { toolBarItem ->

                    IconButton(
                        onClick = toolBarItem.action
                    ) {
                        Icon(
                            painter = painterResource(id = toolBarItem.icon),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.background,
                        )
                    }

                }
            }
        }
    }
}