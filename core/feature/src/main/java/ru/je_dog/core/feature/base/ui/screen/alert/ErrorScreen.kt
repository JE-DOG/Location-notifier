package ru.je_dog.core.feature.base.ui.screen.alert

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.je_dog.core.feature.R
import ru.je_dog.core.feature.base.ui.theme.LocationNotifierTheme

@Composable
@Preview(
    showBackground = true
)
private fun ErrorScreenPreview() {
    LocationNotifierTheme {
        ErrorScreen(
            {}
        )
    }
}

@Composable
fun ErrorScreen(
    onRetryClick: () -> Unit
) {

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            modifier = Modifier
                .size(100.dp),
            painter = painterResource(R.drawable.ic_error_fill),
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = "error"
        )

        Spacer(Modifier.height(15.dp))

        Text(
            text = stringResource(R.string.something_went_wrong),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(Modifier.height(5.dp))

        Button(
            onClick = onRetryClick
        ){
            Text(
                stringResource(R.string.retry),
                color = MaterialTheme.colorScheme.onBackground
            )
        }

    }

}