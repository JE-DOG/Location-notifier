package ru.je_dog.set_geo_point.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.osmdroid.util.GeoPoint
import ru.je_dog.core.feature.base.service.location.LocationManager
import ru.je_dog.core.feature.model.BaseLocation
import ru.je_dog.set_geo_point.vm.components.SetGeoPointAction
import ru.je_dog.set_geo_point.vm.components.SetGeoPointEffect
import ru.je_dog.set_geo_point.vm.components.SetGeoPointMutation
import ru.je_dog.set_geo_point.vm.components.SetGeoPointReducer
import javax.inject.Inject

class SetGeoPointViewModel @Inject constructor(
    private val locationManager: LocationManager
): ViewModel() {

    private val _state = MutableStateFlow(SetGeoPointViewState())
    val state: StateFlow<SetGeoPointViewState> = _state

    private val _effect: MutableStateFlow<SetGeoPointEffect?> = MutableStateFlow(null)
    val effect: StateFlow<SetGeoPointEffect?> = _effect

    private val reducer = SetGeoPointReducer()

    init {
        broadcastGpsState()
    }

    fun action(action: SetGeoPointAction.UI) {
        when(action) {

            is SetGeoPointAction.UI.ShowUserLocation -> {
                showUserLocation()
            }

            is SetGeoPointAction.UI.ChangeGoalLocation -> {
                changeGoalLocation(action.location)
            }

            is SetGeoPointAction.UI.OnConfirm -> {
                onConfirm()
            }

        }
    }

    private fun onConfirm() {
        _effect.update {
            SetGeoPointEffect.ReturnLocation
        }
    }

    private fun changeGoalLocation(location: BaseLocation) {
        SetGeoPointMutation.SetGoalLocation(location)
            .reduce()
    }

    private fun showUserLocation() {
        viewModelScope.launch {
            withContext(Dispatchers.Main){
                val currentLocation = locationManager.getCurrentLocation()

                currentLocation?.let {
                    val geoPoint = GeoPoint(
                        currentLocation.latitude,
                        currentLocation.longitude
                    )

                    SetGeoPointMutation.SetUserGeoPoint(geoPoint)
                        .reduce()

                    delay(50)
                    SetGeoPointMutation.SetUserGeoPoint(null)
                        .reduce()
                }
            }
        }
    }

    private fun action(action: SetGeoPointAction.Internal) {
        when(action) {

            is SetGeoPointAction.Internal.GpsStateChange -> {
                SetGeoPointMutation.SetCanLocationState(action.gpsState)
                    .reduce()
            }

        }
    }

    private fun broadcastGpsState(){
        locationManager.gpsStatusBroadcast()
            .onEach { isGpsEnable ->
                val action = SetGeoPointAction.Internal.GpsStateChange(isGpsEnable)
                action(action)
            }
            .flowOn(Dispatchers.Main)
            .launchIn(viewModelScope)
    }

    private fun SetGeoPointMutation.reduce(){
        _state.update { currentState ->
            reducer.invoke(
                mutation = this,
                currentViewState = currentState,
            )
        }
    }
}