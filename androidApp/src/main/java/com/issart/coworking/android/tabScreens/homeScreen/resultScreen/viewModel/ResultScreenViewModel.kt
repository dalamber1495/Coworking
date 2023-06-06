package com.issart.coworking.android.tabScreens.homeScreen.resultScreen.viewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issart.coworking.android.R
import com.issart.coworking.android.domain.repositories.local.filterResults.SetFiltersResult
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.FilterUiState
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.ResultScreenEvents
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.ResultState
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.RoomUiState
import kotlinx.coroutines.flow.*
import java.time.OffsetDateTime

class ResultScreenViewModel(
    private val setFiltersResult: SetFiltersResult
) : ViewModel() {

    private val _filter = MutableStateFlow(FilterUiState())
    private val _state = MutableStateFlow(ResultState())
    val state = combine(_state, _filter) { state, filter ->
        state.copy(rooms = state.rooms.filter { it.doesMatchFilter(filter) }, filters = filter)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ResultState())

    init {
        setFiltersResult.filters.onEach {
            _filter.emit(it)
        }.launchIn(viewModelScope)

        getRooms().onEach {
            _state.emit(_state.value.copy(rooms = it))
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: ResultScreenEvents) {
        when (event) {
            is ResultScreenEvents.SetFilter -> {
                _filter.tryEmit(event.filter)
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

