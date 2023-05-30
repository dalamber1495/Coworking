package com.issart.coworking.android.tabScreens.homeScreen.detailScreen.viewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.issart.coworking.android.R
import com.issart.coworking.android.tabScreens.homeScreen.detailScreen.data.DetailScreenEvents
import com.issart.coworking.android.tabScreens.homeScreen.detailScreen.data.RoomDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.OffsetDateTime

class DetailScreenViewModel : ViewModel() {

    private val _state = MutableStateFlow(RoomDetailUiState())
    val state = _state.asStateFlow()

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
            date = OffsetDateTime.now(),
            time = Pair(OffsetDateTime.now().minusHours(2L), OffsetDateTime.now()),
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