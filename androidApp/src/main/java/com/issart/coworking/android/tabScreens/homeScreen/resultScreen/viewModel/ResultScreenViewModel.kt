package com.issart.coworking.android.tabScreens.homeScreen.resultScreen.viewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issart.coworking.android.R
import com.issart.coworking.android.domain.repositories.local.filterResults.SetFiltersResult
import com.issart.coworking.android.domain.repositories.local.geoMapResult.SetGeoMapResult
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.FilterUiState
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.ResultScreenEvents
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.ResultState
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.RoomUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

class ResultScreenViewModel(
    private val setFiltersResult: SetFiltersResult,
    private val setGeoMapResult: SetGeoMapResult
) : ViewModel() {

    private val _filterOnBottomSheet = MutableStateFlow(
        FilterUiState(
            dateFilter = LocalDate.now(),
            timeFilter = Pair(
                LocalTime.of(LocalTime.now().hour + 1, 0),
                LocalTime.of(LocalTime.now().hour + 2, 0)
            ),
            peopleFilter = 1, roomFilter = false, multimediaFilter = false
        )
    )
    val filterOnBottomSheet = _filterOnBottomSheet.asStateFlow()
    private val _filter = MutableStateFlow(FilterUiState())
    private val _state = MutableStateFlow(ResultState())
    val state = combine(_state, _filter) { state, filter ->
        state.copy(rooms = state.rooms.filter { it.doesMatchFilter(filter) }, filters = filter)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ResultState())

    init {
        setFiltersResult.filters.onEach {
            _filter.emit(it)
        }.launchIn(viewModelScope)

        setGeoMapResult.geoAddress.onEach {
            _filterOnBottomSheet.emit(_filterOnBottomSheet.value.copy(geoAddress = it.Address))
        }.launchIn(viewModelScope)
        getRooms().onEach {
            _state.emit(_state.value.copy(rooms = it))
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: ResultScreenEvents) {
        when (event) {
            is ResultScreenEvents.SetFilter -> {
                viewModelScope.launch {
                    setFiltersResult.setFilters(event.filter)
                }
            }
            is ResultScreenEvents.SetDatePicker -> {
                viewModelScope.launch {
                    _filterOnBottomSheet.emit(_filterOnBottomSheet.value.copy(dateFilter = event.text))
                }
            }
            is ResultScreenEvents.SetLocate -> {
                viewModelScope.launch {
                    _filterOnBottomSheet.emit(_filterOnBottomSheet.value.copy(geoAddress = event.text))
                }
            }
            ResultScreenEvents.SetMultimedia -> {
                viewModelScope.launch {
                    _filterOnBottomSheet.emit(
                        _filterOnBottomSheet.value.copy(
                            multimediaFilter = when (_filterOnBottomSheet.value.multimediaFilter) {
                                true -> false
                                false -> true
                                else -> true
                            }
                        )
                    )
                }
            }
            is ResultScreenEvents.SetPeoplePicker -> {
                viewModelScope.launch {
                    _filterOnBottomSheet.emit(
                        _filterOnBottomSheet.value.copy(
                            peopleFilter = event.text
                        )
                    )
                }
            }
            ResultScreenEvents.SetRoom -> {
                viewModelScope.launch {
                    _filterOnBottomSheet.emit(
                        _filterOnBottomSheet.value.copy(
                            roomFilter = when (_filterOnBottomSheet.value.roomFilter) {
                                true -> false
                                false -> true
                                else -> true
                            }
                        )
                    )
                }
            }
            is ResultScreenEvents.SetTimeEndPicker -> {
                viewModelScope.launch {
                    _filterOnBottomSheet.emit(
                        _filterOnBottomSheet.value.copy(
                            timeFilter = Pair(
                                _filterOnBottomSheet.value.timeFilter?.first ?: LocalTime.now(),
                                event.text
                            )
                        )
                    )
                }
            }
            is ResultScreenEvents.SetTimeStartPicker -> {
                viewModelScope.launch {
                    _filterOnBottomSheet.emit(
                        _filterOnBottomSheet.value.copy(
                            timeFilter = Pair(
                                event.text,
                                _filterOnBottomSheet.value.timeFilter?.second ?: LocalTime.now()
                            )
                        )
                    )
                }
            }
            ResultScreenEvents.ApplyFilters -> {
                viewModelScope.launch {
                    setFiltersResult.setFilters(_filterOnBottomSheet.value)
                }
            }
        }
    }

    private fun getRooms(): Flow<List<RoomUiState>> {
        return flow {
            val mutaList = mutableListOf<RoomUiState>()

            repeat(20) {
                mutaList.add(
                    RoomUiState(
                        name = "Room number 1",
                        title = "coworking Worki",
                        like = false,
                        coast = 2000f,
                        wifi = true,
                        display = true,
                        laptop = false,
                        projector = true,
                        printer = true,
                        photoUri = Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room),
                        room = false
                    )
                )
            }
            emit(
                mutaList
            )
        }
    }
}

