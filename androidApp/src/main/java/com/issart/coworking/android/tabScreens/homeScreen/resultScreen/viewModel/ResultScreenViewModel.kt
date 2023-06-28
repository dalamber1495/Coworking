package com.issart.coworking.android.tabScreens.homeScreen.resultScreen.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issart.coworking.android.domain.repositories.local.filterResults.SetFiltersResult
import com.issart.coworking.android.domain.repositories.local.geoMapResult.SetGeoMapResult
import com.issart.coworking.android.domain.repositories.local.useCases.AddRoomInHistoryUseCase
import com.issart.coworking.android.domain.repositories.local.useCases.GetRoomListUseCase
import com.issart.coworking.android.domain.repositories.local.useCases.UpdateRoomUseCase
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.FilterUiState
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.ResultScreenEvents
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.ResultState
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.RoomUiState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.time.LocalDate
import java.time.LocalTime

class ResultScreenViewModel(
    private val setFiltersResult: SetFiltersResult,
    private val setGeoMapResult: SetGeoMapResult,
    private val getRoomListUseCase: GetRoomListUseCase,
    private val updateRoomUseCase: UpdateRoomUseCase,
    private val addRoomInHistoryUseCase: AddRoomInHistoryUseCase
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

    private var _stateList = mutableStateListOf<RoomUiState>()
    private val _listRooms = MutableStateFlow(_stateList)
    private val _state = MutableStateFlow(ResultState())
    val state = combine(_state, _listRooms, _filter) { state, listRooms, filter ->
        state.copy(rooms = listRooms.filter { it.doesMatchFilter(filter) }, filters = filter)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ResultState())

    init {
        setFiltersResult.filters.onEach {
            _filter.emit(it)
        }.launchIn(viewModelScope)

        setGeoMapResult.geoAddress.onEach {
            _filterOnBottomSheet.emit(_filterOnBottomSheet.value.copy(geoAddress = it.Address))
        }.launchIn(viewModelScope)

        viewModelScope.launch {
            getRoomListUseCase.invoke()
        }
        getRoomListUseCase.roomData.onEach {
            _stateList = it.toMutableStateList()
            _listRooms.value = _stateList

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
            ResultScreenEvents.UpdateListState -> {

            }
            is ResultScreenEvents.SetLikeOnRoom -> {
                viewModelScope.launch {
                    _stateList[event.id] =
                        _stateList[event.id].copy(like = !_stateList[event.id].like)
                    _state.value = _state.value.copy(rooms = _stateList.toList())

                    updateRoomUseCase.invoke(_listRooms.value[event.id]).single()
                }
            }
            is ResultScreenEvents.AddRoomInHistory -> {
                viewModelScope.launch {
                    if (_filter.value.dateFilter != null && _filter.value.timeFilter != null)
                        addRoomInHistoryUseCase.invoke(event.id, _filter.value.dateFilter!!, _filter.value.timeFilter!!)
                }
            }
        }
    }


}

