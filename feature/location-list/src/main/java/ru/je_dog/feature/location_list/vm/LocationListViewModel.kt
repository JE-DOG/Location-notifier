package ru.je_dog.feature.location_list.vm

import android.content.Context
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.je_dog.core.ext.add
import ru.je_dog.core.ext.remove
import ru.je_dog.core.ext.updateItem
import ru.je_dog.core.feature.base.vm.BaseViewModel
import ru.je_dog.core.feature.model.GeoPointPresentation
import ru.je_dog.domain.location_list.use_case.*
import ru.je_dog.feature.location_list.vm.reducer.LocationListMutation
import ru.je_dog.feature.location_list.vm.reducer.LocationListReducer
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Qualifier

internal class LocationListViewModel @Inject constructor(
    private val getAllLocationUseCase: GetAllLocationUseCase,
    private val addLocationUseCase: AddLocationUseCase,
    private val updateLocationUseCase: UpdateLocationUseCase,
    private val deleteLocationUseCase: DeleteLocationUseCase,
    private val deleteAllLocationUseCase: DeleteAllLocationUseCase
): BaseViewModel<LocationListViewState,LocationListMutation,LocationListReducer>(
    MutableStateFlow(LocationListViewState())
) {

    override val reducer = LocationListReducer()

    fun action(action: LocationListAction) {

        viewModelScope.launch(Dispatchers.IO) {

            when (action) {

                is LocationListAction.DeleteLocation -> {
                    val isDeleteSuccess = deleteLocationUseCase.execute(action.geoPoint.toDomain())

                    if (isDeleteSuccess){

                        val newList = state.value.locations.remove(action.geoPoint)

                        LocationListMutation.ShowLocations(
                            newList
                        ).reduce()
                    }
                }

                is LocationListAction.AddLocation -> {

                    val isAddSuccess = addLocationUseCase.execute(action.geoPoint.toDomain())

                    if (isAddSuccess){
                        val newList = state.value.locations.add(action.geoPoint)
                        LocationListMutation.ShowLocations(
                            newList
                        ).reduce()
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
                        ).reduce()
                    }
                }

                is LocationListAction.DeleteAllLocation -> {
                    val isDeleteAllSuccess = deleteAllLocationUseCase.execute()

                    if (isDeleteAllSuccess){
                        LocationListMutation.ShowLocations(
                            emptyList()
                        ).reduce()
                    }
                }

                is LocationListAction.GetAllLocation -> {
                    getAllLocation()
                }

            }

        }

    }

    private suspend fun getAllLocation() {

        getAllLocationUseCase.execute()
            .onStart {
                LocationListMutation.ShowLoading.reduce()
            }
            .catch {
                LocationListMutation.ShowError.reduce()
            }
            .map { locationsDomain ->
                locationsDomain.map { GeoPointPresentation.fromDomain(it) }
            }
            .collect { locations ->
                LocationListMutation.ShowLocations(
                    locations
                ).reduce()
            }

    }

}