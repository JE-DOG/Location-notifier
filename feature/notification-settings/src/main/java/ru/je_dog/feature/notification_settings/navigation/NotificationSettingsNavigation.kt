package ru.je_dog.feature.notification_settings.navigation

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.je_dog.core.feature.base.app.tool_bar.AppToolBar
import ru.je_dog.core.feature.base.app.tool_bar.AppToolBarItem
import ru.je_dog.feature.notification_settings.NotificationScreen
import ru.je_dog.feature.notification_settings.di.DaggerNotificationSettingsComponent
import ru.je_dog.feature.notification_settings.di.deps.NotificationSettingsComponentDepsProvider

fun NavGraphBuilder.notificationSettings(
    changeToolBar: (AppToolBar) -> Unit,
    navigateToBack: () -> Unit
) {
    composable(
        route = NOTIFICATION_SETTINGS_ROUTE,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(
                    durationMillis = 500,
                    easing = LinearOutSlowInEasing
                )
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(
                    durationMillis = 500,
                    easing = LinearOutSlowInEasing
                )
            )
        }
    ){
        val context = LocalContext.current
        val component = remember {
            val deps = NotificationSettingsComponentDepsProvider.deps

            DaggerNotificationSettingsComponent.factory()
                .create(deps)
        }
        val notificationSettingsViewModel = remember(component) {
            component.notificationSettingsViewModel
        }

        NotificationScreen(
            viewModel = notificationSettingsViewModel
        )

        LaunchedEffect(Unit){
            val appToolBar = AppToolBar(
                title = context.getString(ru.je_dog.core.feature.R.string.notification_settings_screen),
                starItem = AppToolBarItem.back(navigateToBack)
            )
            changeToolBar(appToolBar)
        }
    }
}

fun NavController.navigateToNotificationSettings() {
    navigate(NOTIFICATION_SETTINGS_ROUTE)
}

const val NOTIFICATION_SETTINGS_ROUTE = "NOTIFICATION_SETTINGS_ROUTE"