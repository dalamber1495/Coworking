package com.issart.coworking.android.tabScreens.profileScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issart.coworking.android.domain.repositories.local.useCases.AddRoomInHistoryUseCase
import com.issart.coworking.android.domain.repositories.local.useCases.AddRoomInReservedUseCase
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.RoomUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ProfileScreenViewModel(
    private val addRoomInReservedUseCase: AddRoomInReservedUseCase,
    private val addRoomInHistoryUseCase: AddRoomInHistoryUseCase
):ViewModel() {

    private val _historyListState = MutableStateFlow<List<RoomUiState>?>(null)
    val historyListState = _historyListState.asStateFlow()
    private val _reservedListState = MutableStateFlow<List<RoomUiState>?>(null)
    val reservedListState = _reservedListState.asStateFlow()

    init {
        addRoomInHistoryUseCase.roomData.onEach {
            _historyListState.value = it
        }.launchIn(viewModelScope)
        addRoomInReservedUseCase.roomData.onEach {
            _reservedListState.value = it
        }.launchIn(viewModelScope)
    }

}