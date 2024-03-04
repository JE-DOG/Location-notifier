package ru.je_dog.core.feature.common.ui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.je_dog.core.feature.app.tool_bar.ToolBar
import ru.je_dog.core.feature.app.tool_bar.ToolBarTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    toolBar: ToolBar?
) {
    if(toolBar != null){
        CenterAlignedTopAppBar(
            title = {
                val title = toolBar.title
                when(title) {

                    is ToolBarTitle.Text -> {
                        Text(
                            text = stringResource(id = title.text),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.background
                        )
                    }

                }
            },
            navigationIcon = {
                val toolBarAction = toolBar.startToolBarAction
                if (toolBarAction != null){
                    IconButton(
                        onClick = toolBarAction.action
                    ) {
                        Icon(
                            painter = painterResource(id = toolBarAction.icon),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.background
                        )
                    }
                }
            },
            actions = {
                toolBar.endToolBarActions?.forEach {  toolBarAction ->
                    IconButton(
                        onClick = toolBarAction.action
                    ) {
                        Icon(
                            painter = painterResource(id = toolBarAction.icon),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.background
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
            )
        )
    }

}