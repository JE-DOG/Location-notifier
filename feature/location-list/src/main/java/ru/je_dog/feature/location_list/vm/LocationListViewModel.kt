package ru.je_dog.feature.location_list.vm

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.je_dog.core.ext.remove
import ru.je_dog.core.ext.updateItem
import ru.je_dog.core.feature.base.vm.BaseViewModel
import ru.je_dog.core.feature.model.GeoPointPresentation
import ru.je_dog.domain.location_list.use_case.*
import ru.je_dog.feature.location_list.elements.state.LocationListViewState
import ru.je_dog.feature.location_list.vm.reducer.LocationListMutation
import ru.je_dog.feature.location_list.vm.reducer.LocationListReducer
import javax.inject.Inject

internal class LocationListViewModel @Inject constructor(
    private val getAllLocationUseCase: GetAllLocationUseCase,
    private val addLocationUseCase: AddLocationUseCase,
    private val updateLocationUseCase: UpdateLocationUseCase,
    private val deleteLocationUseCase: DeleteLocationUseCase,
    private val deleteAllLocationUseCase: DeleteAllLocationUseCase
): BaseViewModel<LocationListViewState,LocationListMutation,LocationListReducer>(
    LocationListReducer()
) {
    private val _state = MutableStateFlow(LocationListViewState())
    val state: StateFlow<LocationListViewState> = _state

    init {
        action(LocationListAction.GetAllLocation)
    }

    fun action(action: LocationListAction) {
        viewModelScope.launch(Dispatchers.IO) {
            when (action) {
                is LocationListAction.DeleteLocation -> {
                    val isDeleteSuccess = deleteLocationUseCase.execute(action.geoPoint.toDomain())

                    if (isDeleteSuccess){
                        val newList = state.value.locations.remove(action.geoPoint)

                        LocationListMutation.ShowLocations(
                            newList
                        ).reduce(_state)
                    }
                }

                is LocationListAction.AddLocation -> {
                    val isAddSuccess = addLocationUseCase.execute(action.geoPoint.toDomain())

                    if (isAddSuccess){
                        getAllLocation()
                    }
                }

                is LocationListAction.UpdateLocation -> {
                    val isUpdateSuccess = updateLocationUseCase.execute(action.geoPoint.toDomain())
                    if (isUpdateSuccess){
                        val newList = state.value.locations.updateItem(
                            item = action.geoPoint,
                            findBy = {
                                it.id == action.geoPoint.id
                            }
                        )

                        LocationListMutation.ShowLocations(
                            newList
                        ).reduce(_state)
                    }
                }

                is LocationListAction.DeleteAllLocation -> {
                    val isDeleteAllSuccess = deleteAllLocationUseCase.execute()

                    if (isDeleteAllSuccess){
                        LocationListMutation.ShowLocations(
                            emptyList()
                        ).reduce(_state)
                    }
                }

                is LocationListAction.GetAllLocation -> {
                    getAllLocation()
                }

                is LocationListAction.ShowGeoPointDialog -> {
                    LocationListMutation.ShowGeoPointDialog(
                        action.geoPointDialogState
                    ).reduce(_state)
                }

                is LocationListAction.HideGeoPointDialog -> {
                    LocationListMutation.HideGeoPointDialog.reduce(_state)
                }

                is LocationListAction.SetLocationForGeoPointDialog -> {
                    LocationListMutation.SetLocationForGeoPointDialog(action.location).reduce(_state)
                }
            }
        }
    }

    private suspend fun getAllLocation() {
        getAllLocationUseCase.execute()
            .onStart {
                LocationListMutation.ShowLoading.reduce(_state)
            }
            .catch {
                LocationListMutation.ShowError.reduce(_state)
            }
            .map { locationsDomain ->
                locationsDomain.map { GeoPointPresentation.fromDomain(it) }
            }
            .collect { locations ->
                LocationListMutation.ShowLocations(
                    locations
                ).reduce(_state)
            }

    }

}