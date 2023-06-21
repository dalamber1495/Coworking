package com.issart.coworking.android.domain.repositories.local.useCases

import android.net.Uri
import com.issart.coworking.android.R
import com.issart.coworking.android.domain.repositories.local.getRoomList.RoomRepository
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.RoomUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow

class GetRoomListUseCase(
    private val roomRepository: RoomRepository
) {

    private val _roomData = MutableSharedFlow<List<RoomUiState>>(replay = 1, extraBufferCapacity = 1)
    val roomData = _roomData.asSharedFlow()

    suspend fun invoke() {
            val rooms = roomRepository.getRooms()
            _roomData.emit(rooms)
    }
}