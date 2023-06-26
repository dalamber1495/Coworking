package com.issart.coworking.android.tabScreens.homeScreen.reservedScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issart.coworking.android.domain.repositories.local.filterResults.SetFiltersResult
import com.issart.coworking.android.domain.repositories.local.useCases.GetRoomByIdUseCase
import com.issart.coworking.android.tabScreens.homeScreen.detailScreen.data.DetailScreenEvents
import com.issart.coworking.android.tabScreens.homeScreen.reservedScreen.data.ReservedScreenEvents
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.RoomUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ReservedScreenViewModel(
    private val getRoomByIdUseCase: GetRoomByIdUseCase,
    private val setFiltersResult: SetFiltersResult,
    ) : ViewModel() {


    private val _state = MutableStateFlow(RoomUiState())
    val state = _state.asStateFlow()

    init {
        setFiltersResult.filters.onEach {
            _state.emit(_state.value.copy(date = it.dateFilter, time = it.timeFilter))
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: ReservedScreenEvents) {
        when (event) {
            is ReservedScreenEvents.GetRoomState -> {
                getRoomByIdUseCase.invoke(event.id).onEach {
                    _state.emit(it.copy(date = _state.value.date, time = _state.value.time))
                }.launchIn(viewModelScope)
            }
        }
    }

}