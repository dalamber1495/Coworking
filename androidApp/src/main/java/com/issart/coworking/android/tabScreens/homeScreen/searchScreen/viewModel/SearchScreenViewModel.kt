package com.issart.coworking.android.tabScreens.homeScreen.searchScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issart.coworking.android.domain.repositories.local.filterResults.SetFiltersResult
import com.issart.coworking.android.domain.repositories.local.geoMapResult.SetGeoMapResult
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.FilterUiState
import com.issart.coworking.android.tabScreens.homeScreen.searchScreen.model.SearchScreenUiEvents
import com.issart.coworking.android.tabScreens.homeScreen.searchScreen.model.SearchScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class SearchScreenViewModel(
    private val setGeoMapResult: SetGeoMapResult,
    private val setFiltersResult: SetFiltersResult
) : ViewModel() {

    private val _state = MutableStateFlow(SearchScreenUiState())
    val state = _state.asStateFlow()


    init {
        viewModelScope.launch {
            setGeoMapResult.geoAddress.onEach {
                if(it.Address == null)
                    _state.emit(_state.value.copy(geoCoding = "${it.latitude.roundToInt()/100.0} ${it.longitude.roundToInt()/100.0}"))
                else
                    _state.emit(_state.value.copy(geoCoding = it.Address))
            }.collect()
        }
    }
    fun onEvent(event: SearchScreenUiEvents) {

        when (event) {
            is SearchScreenUiEvents.SetDatePicker -> {
                viewModelScope.launch {
                    _state.emit(_state.value.copy(date = event.text))
                }
            }
            is SearchScreenUiEvents.SetLocate -> {
                viewModelScope.launch {
                    _state.emit(_state.value.copy(geoCoding = event.text))
                }
            }
            is SearchScreenUiEvents.SetMultimedia -> {
                viewModelScope.launch {
                    _state.emit(_state.value.copy(multimedia = !_state.value.multimedia))
                }
            }
            is SearchScreenUiEvents.SetPeoplePicker -> {
                viewModelScope.launch {
                    _state.emit(_state.value.copy(people = event.text))
                }
            }
            is SearchScreenUiEvents.SetRoom -> {
                viewModelScope.launch {
                    _state.emit(_state.value.copy(room = !_state.value.room))
                }
            }
            is SearchScreenUiEvents.SetTimeStartPicker -> {
                viewModelScope.launch {
                    _state.emit(_state.value.copy(timeStart = event.text))
                }
            }
            is SearchScreenUiEvents.SetTimeEndPicker -> {
                viewModelScope.launch {
                    _state.emit(_state.value.copy(timeEnd = event.text))
                }
            }
            SearchScreenUiEvents.ClickFindButton -> {
                viewModelScope.launch {
                    _state.value.apply {
                        setFiltersResult.setFilters(
                            FilterUiState(
                                dateFilter = date,
                                timeFilter = Pair(timeStart,timeEnd),
                                peopleFilter = people,
                                roomFilter = room,
                                multimediaFilter = multimedia,
                                geoAddress = geoCoding
                            )
                        )
                    }
                }
            }
        }
    }

}