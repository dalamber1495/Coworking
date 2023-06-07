package com.issart.coworking.android.tabScreens.homeScreen.detailScreen.viewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issart.coworking.android.R
import com.issart.coworking.android.domain.repositories.local.filterResults.SetFiltersResult
import com.issart.coworking.android.tabScreens.homeScreen.detailScreen.data.DetailScreenEvents
import com.issart.coworking.android.tabScreens.homeScreen.detailScreen.data.RoomDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate
import java.time.LocalTime
import java.time.OffsetDateTime

class DetailScreenViewModel (
    private val setFiltersResult: SetFiltersResult
        ): ViewModel() {

    private val _state = MutableStateFlow(RoomDetailUiState())
    val state = _state.asStateFlow()


    init {
        setFiltersResult.filters.onEach {
            _state.emit(_state.value.copy(date = it.dateFilter, time = it.timeFilter))
        }.launchIn(viewModelScope)
    }
    fun onEvent(event: DetailScreenEvents) {
        when (event) {
            is DetailScreenEvents.GetRoomState -> {
                _state.tryEmit(getCoworkingById(event.id))
            }
        }
    }


    private fun getCoworkingById(id: Int): RoomDetailUiState {
        return RoomDetailUiState(
            name = "Комната",
            title = "Заголовок",
            description = "Описание",
            like = true,
            coast = 2000f,
            date = LocalDate.now(),
            time = Pair(LocalTime.now().minusHours(2L), LocalTime.now()),
            wifi = true,
            display = true,
            laptop = true,
            projector = true,
            printer = true,
            photoUri = listOf(
                Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.coworking1),
                Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.coworking2),
                Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.coworking3jpg)
            )
        )
    }
}