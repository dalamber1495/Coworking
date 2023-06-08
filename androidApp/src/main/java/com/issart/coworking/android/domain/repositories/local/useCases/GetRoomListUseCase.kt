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

    private val _roomData = MutableSharedFlow<List<RoomUiState>>()
    val roomData = _roomData.asSharedFlow()
    fun invoke(): Flow<List<RoomUiState>> {
        return flow {
            val rooms = roomRepository.getRooms()
            _roomData.emit(rooms)
            emit(rooms)
        }
    }
}