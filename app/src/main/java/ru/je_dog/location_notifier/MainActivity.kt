package ru.je_dog.location_notifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.je_dog.core.feature.base.ui.theme.LocationNotifierTheme
import ru.je_dog.core.feature.base.app.vm.AppViewModel
import ru.je_dog.location_notifier.ui.AppUi
import ru.je_dog.location_notifier.ui.permission.vm.PermissionViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appViewModel: AppViewModel
    @Inject
    lateinit var permissionViewModel: PermissionViewModel
    private val appComponent = App.INSTANCE.appComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        permissionViewModel.init(this)
        super.onCreate(savedInstanceState)

        setContent {
            LocationNotifierTheme {
                val appState by appViewModel.state.collectAsState()
                val permissionState by permissionViewModel.state.collectAsState()

                AppUi(
                    appState = appState,
                    permissionState = permissionState,
                    changeToolBar = appViewModel::changeToolbar,
                    onPermissionResult = permissionViewModel::onPermissionResult
                )
            }
        }
    }
}