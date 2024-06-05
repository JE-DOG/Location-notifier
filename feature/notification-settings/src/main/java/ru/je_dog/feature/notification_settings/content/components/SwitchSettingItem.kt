package ru.je_dog.feature.notification_settings.content.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.annotations.VisibleForTesting
import ru.je_dog.core.feature.base.ui.theme.LocationNotifierPreviewTheme

//region Preview
@VisibleForTesting
@Composable
@Preview(
    showBackground = true
)
private fun SwitchSettingItemPreview() {
    LocationNotifierPreviewTheme {
        Column {
            Text(
                text = "Only Title(select)",
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(10.dp))

            SwitchSettingItem(
                title = "This is title",
                state = true
            )

            HorizontalDivider()

            Text(
                text = "Title + description(unselected)",
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(10.dp))

            SwitchSettingItem(
                title = "This is title",
                description = "This is description",
            )

            HorizontalDivider()

            Text(
                text = "Title + big description(selected)",
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(10.dp))

            SwitchSettingItem(
                title = "This is title",
                description = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa",
                state = true,
            )
        }
    }
}
//endregion

@Composable
internal fun SwitchSettingItem(
    modifier: Modifier = Modifier,
    title: String,
    state: Boolean = false,
    onValueChange: (Boolean) -> Unit = {},
    description: String? = null,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )

            if (description != null) {
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }

        Spacer(modifier = Modifier.width(15.dp))

        Switch(
            modifier = Modifier
                .align(Alignment.Top),
            checked = state,
            onCheckedChange = onValueChange,
        )
    }
}