package com.issart.coworking.android.domain.repositories.local.useCases

import com.issart.coworking.android.domain.repositories.local.getRoomList.RoomRepository
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.RoomUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import java.time.LocalDate
import java.time.LocalTime

class AddRoomInReservedUseCase(
    private val roomRepository: RoomRepository
) {

    private val _roomData =
        MutableSharedFlow<List<RoomUiState>>(replay = 1, extraBufferCapacity = 1)
    val roomData = _roomData.asSharedFlow()

    suspend fun invoke(id: Int, date: LocalDate, time: Pair<LocalTime, LocalTime>) {
        val rooms = roomRepository.addRoomInReserved(id, date, time)
        _roomData.emit(rooms)
    }

}