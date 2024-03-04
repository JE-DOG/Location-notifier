package ru.je_dog.core.feature.app.tool_bar

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import ru.je_dog.core.feature.R

@Stable
data class ToolBar(
    val title: ToolBarTitle,
    val startToolBarAction: ToolBarAction?,
    /**
     * Items will placed by start to end
     *
     * Example: List(delete, more). On the screen: delete more
     * */
    val endToolBarActions: List<ToolBarAction>?
)

data class ToolBarAction(
    @DrawableRes
    val icon: Int,
    val action: () -> Unit
){
    companion object {

        fun back(action: () -> Unit) = ToolBarAction(R.drawable.ic_back_arrow,action)


    }
}
