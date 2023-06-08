package com.issart.coworking.android.domain.repositories.local.getRoomList

import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.RoomUiState
import kotlinx.coroutines.flow.Flow

interface RoomRepository {
    suspend fun getRooms(): List<RoomUiState>
    suspend fun getRoomById(id:Int): RoomUiState?
    suspend fun updateRoom(room:RoomUiState):RoomUiState
}