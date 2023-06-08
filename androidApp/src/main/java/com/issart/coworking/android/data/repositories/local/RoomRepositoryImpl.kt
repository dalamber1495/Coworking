package com.issart.coworking.android.data.repositories.local

import android.net.Uri
import com.issart.coworking.android.R
import com.issart.coworking.android.domain.repositories.local.getRoomList.RoomRepository
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.RoomUiState


class RoomRepositoryImpl : RoomRepository {

    private val roomList = getDataRooms()


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
                    time = null
                )
            )
        }
        return roomMap
    }

    override suspend fun updateRoom(room: RoomUiState): RoomUiState {
        roomList[room.id] = room
        return room
    }
}