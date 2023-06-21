package com.issart.coworking.android.tabScreens.homeScreen.detailScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issart.coworking.android.domain.repositories.local.filterResults.SetFiltersResult
import com.issart.coworking.android.domain.repositories.local.useCases.GetRoomByIdUseCase
import com.issart.coworking.android.domain.repositories.local.useCases.GetRoomListUseCase
import com.issart.coworking.android.domain.repositories.local.useCases.UpdateRoomUseCase
import com.issart.coworking.android.tabScreens.homeScreen.detailScreen.data.DetailScreenEvents
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.RoomUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailScreenViewModel(
    private val setFiltersResult: SetFiltersResult,
    private val updateRoomUseCase: UpdateRoomUseCase,
    private val getRoomByIdUseCase: GetRoomByIdUseCase,
    private val getRoomListUseCase: GetRoomListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RoomUiState())
    val state = _state.asStateFlow()


    init {
        setFiltersResult.filters.onEach {
            _state.emit(_state.value.copy(date = it.dateFilter, time = it.timeFilter))
        }.launchIn(viewModelScope)
    }
    fun onEvent(event: DetailScreenEvents) {
        when (event) {
            is DetailScreenEvents.GetRoomState -> {
                getRoomByIdUseCase.invoke(event.id).onEach {
                    _state.emit(it)
                }.launchIn(viewModelScope)
            }
            is DetailScreenEvents.SetLikeOnRoom -> {
                viewModelScope.launch {
                    _state.emit(updateRoomUseCase.invoke(_state.value.copy(like = event.like)).single())
                    getRoomListUseCase.invoke()
                }
            }
        }
    }



}