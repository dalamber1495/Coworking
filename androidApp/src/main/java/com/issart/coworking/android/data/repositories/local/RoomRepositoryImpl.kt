package com.issart.coworking.android.data.repositories.local

import android.net.Uri
import com.issart.coworking.android.R
import com.issart.coworking.android.domain.repositories.local.getRoomList.RoomRepository
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.RoomUiState
import java.time.LocalDate
import java.time.LocalTime


class RoomRepositoryImpl : RoomRepository {

    private val roomList = getDataRooms()
    private val historyList = mutableListOf<RoomUiState>()
    private val reservedList = mutableListOf<RoomUiState>()


    override suspend fun getRooms(): List<RoomUiState> {
        return roomList.map { it.value }
    }

    override suspend fun getRoomById(id: Int): RoomUiState? {
        return roomList[id]
    }

    private fun getDataRooms(): MutableMap<Int, RoomUiState> {

        val roomMap = mutableMapOf<Int, RoomUiState>()
        (0..20).map {
            roomMap.put(
                it,
                RoomUiState(
                    id = it,
                    name = "Room number ${it + 1}",
                    title = "coworking Worki",
                    like = false,
                    coast = 2000f,
                    wifi = true,
                    display = true,
                    laptop = false,
                    projector = true,
                    printer = true,
                    photoUri = listOf(
                        Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room),
                        Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.coworking1),
                        Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.coworking2),
                        Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.coworking3jpg)
                    ),
                    room = false,
                    description = "Описание",
                    date = null,
                    time = null,
                    address = "Коворкинг Worki ул. Ленина, 20"
                )
            )
        }
        return roomMap
    }

    override suspend fun updateRoom(room: RoomUiState): RoomUiState {
        roomList[room.id] = room
        return room
    }

    override suspend fun addRoomInHistory(
        id: Int,
        date: LocalDate,
        time: Pair<LocalTime, LocalTime>
    ) :List<RoomUiState>{
        val room = roomList[id]
        room?.let {
            historyList.add(it)
            if (historyList.size > 10)
                historyList.removeFirst()
        }
        return historyList
    }

    override suspend fun addRoomInReserved(
        id: Int,
        date: LocalDate,
        time: Pair<LocalTime, LocalTime>
    ): List<RoomUiState> {
        val room = roomList[id]
        room?.let {
            reservedList.add(it)
        }
        return reservedList
    }


}