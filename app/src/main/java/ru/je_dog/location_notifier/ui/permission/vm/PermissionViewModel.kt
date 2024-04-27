package ru.je_dog.location_notifier.ui.permission.vm

import android.Manifest
import android.content.Context
import android.os.Build
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.je_dog.location_notifier.utill.checkPermissionIsGranted
import javax.inject.Inject

class PermissionViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(PermissionViewState())
    val state: StateFlow<PermissionViewState> = _state

    fun init(context: Context){
        val locationIsGranted = context.checkPermissionIsGranted(
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        val notificationIsGranted = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.checkPermissionIsGranted(
                Manifest.permission.POST_NOTIFICATIONS
            )
        } else {
            null
        }

        _state.update {
            PermissionViewState(
                notificationIsGranted = notificationIsGranted,
                locationIsGranted = locationIsGranted
            )
        }
    }

    fun onPermissionResult(
        permission: String,
        isGranted: Boolean
    ){
        when(permission){

            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION -> {
                _state.update {
                    it.copy(
                        locationIsGranted = isGranted
                    )
                }
            }

            Manifest.permission.POST_NOTIFICATIONS -> {
                _state.update {
                    it.copy(
                        notificationIsGranted = isGranted
                    )
                }
            }

            else -> {}
        }
    }
}