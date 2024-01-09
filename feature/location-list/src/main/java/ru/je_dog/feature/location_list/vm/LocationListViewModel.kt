package ru.je_dog.feature.location_list.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.je_dog.core.feature.model.GeoPointPresentation
import ru.je_dog.domain.location_list.use_case.*
import ru.je_dog.feature.location_list.vm.reducer.Mutation
import ru.je_dog.feature.location_list.vm.reducer.Reducer

internal class LocationListViewModel(
    private val getAllLocationUseCase: GetAllLocationUseCase,
    private val addLocationUseCase: AddLocationUseCase,
    private val updateLocationUseCase: UpdateLocationUseCase,
    private val deleteLocationUseCase: DeleteLocationUseCase,
    private val deleteAllLocationUseCase: DeleteAllLocationUseCase,
): ViewModel() {

    private val reducer = Reducer()
    private val _state = MutableStateFlow(LocationListViewState())
    val state: StateFlow<LocationListViewState> = _state

    fun action(action: Action) {

        viewModelScope.launch(Dispatchers.IO) {

            when (action) {

                is Action.DeleteLocation -> {
                    deleteLocationUseCase.execute(
                        action.geoPointPresentation
                            .toDomain()
                    )
                }

                is Action.AddLocation -> {
                    addLocationUseCase.execute(
                        action.geoPointPresentation
                            .toDomain()
                    )
                }

                is Action.UpdateLocation -> {
                    updateLocationUseCase.execute(
                        action.geoPointPresentation
                            .toDomain()
                    )
                }

                is Action.DeleteAllLocation -> {
                    deleteAllLocationUseCase.execute()
                }

                is Action.GetAllLocation -> {
                    getAllLocation()
                }

            }

        }

    }

    private suspend fun getAllLocation() {

        getAllLocationUseCase.execute()
            .onStart {
                Mutation.ShowLoading.reduce()
            }
            .catch {
                Mutation.ShowError.reduce()
            }
            .map { locationsDomain ->
                locationsDomain.map { GeoPointPresentation.fromDomain(it) }
            }
            .collect { locations ->
                Mutation.ShowLocations(
                    locations
                ).reduce()
            }

    }

    private fun Mutation.reduce(){
        _state.value = reducer.invoke(this,state.value)
    }

}