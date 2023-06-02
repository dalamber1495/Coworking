package com.issart.coworking.android.tabScreens.homeScreen.searchScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issart.coworking.android.tabScreens.homeScreen.searchScreen.model.SearchScreenUiEvents
import com.issart.coworking.android.tabScreens.homeScreen.searchScreen.model.SearchScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchScreenViewModel : ViewModel() {

    private val _state = MutableStateFlow(SearchScreenUiState())
    val state = _state.asStateFlow()


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
                    _state.emit(_state.value.copy(multimedia = event.multimedia))
                }
            }
            is SearchScreenUiEvents.SetPeoplePicker -> {
                viewModelScope.launch {
                    _state.emit(_state.value.copy(people = event.text))
                }
            }
            is SearchScreenUiEvents.SetRoom -> {
                viewModelScope.launch {
                    _state.emit(_state.value.copy(room = event.room))
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
                TODO()
            }
        }
    }

}