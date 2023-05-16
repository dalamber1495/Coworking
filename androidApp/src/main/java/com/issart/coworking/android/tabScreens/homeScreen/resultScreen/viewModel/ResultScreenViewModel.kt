package com.issart.coworking.android.tabScreens.homeScreen.resultScreen.viewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issart.coworking.android.R
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.FilterUiState
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.ResultScreenEvents
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.ResultState
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.RoomUiState
import kotlinx.coroutines.flow.*
import java.time.OffsetDateTime

class ResultScreenViewModel : ViewModel() {

    private val _filter = MutableStateFlow(FilterUiState())
    private val _rooms = _filter.flatMapLatest {
        getRoomsByFilters(it)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(ResultState())
    val state = combine(_state, _rooms, _filter) { state, rooms, filter ->
        state.copy(rooms = rooms, filters = filter)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ResultState())

    init {
        onEvent(
            ResultScreenEvents.SetFilter(
                FilterUiState(
                    dateFilter = OffsetDateTime.now(),
                    timeFilter = Pair(OffsetDateTime.now().minusHours(2L), OffsetDateTime.now()),
                    peopleFilter = 6,
                    roomFilter = true,
                    multimediaFilter = true
                )
            )
        )
    }

    fun onEvent(event: ResultScreenEvents) {
        when (event) {
            is ResultScreenEvents.SetFilter -> {
                _filter.tryEmit(event.filter)
            }
        }
    }

    private fun getRoomsByFilters(state: FilterUiState): Flow<List<RoomUiState>> {
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
                        laptop = true,
                        projector = true,
                        printer = true,
                        photoUri = Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room)
                    )
                )
            }
            emit(
                mutaList
            )
        }
    }
}

