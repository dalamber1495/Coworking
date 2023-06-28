package com.issart.coworking.android.domain.repositories.local.getRoomList

import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.RoomUiState
import java.time.LocalDate
import java.time.LocalTime

interface RoomRepository {
    suspend fun getRooms(): List<RoomUiState>
    suspend fun getRoomById(id:Int): RoomUiState?
    suspend fun updateRoom(room:RoomUiState):RoomUiState

    suspend fun addRoomInHistory(id:Int, date:LocalDate, time: Pair<LocalTime, LocalTime>):List<RoomUiState>

    suspend fun addRoomInReserved(id:Int, date:LocalDate, time: Pair<LocalTime, LocalTime>):List<RoomUiState>

    suspend fun removeRoomFromReserved(id:Int):List<RoomUiState>

}