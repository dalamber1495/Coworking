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
        roomMap.put(
            1,
            RoomUiState(
                id = 1,
                name = "Бизнес инкубатор",
                title = "Коворкинг студия для всех",
                like = false,
                coast = 3000f,
                wifi = true,
                display = true,
                laptop = false,
                projector = true,
                printer = true,
                photoUri = listOf(
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room),
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room12),
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room13),
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room11)
                ),
                room = false,
                description = "Студия для удаленщиков и тех кто хочет сменить обстановку, есть все необходимое для работы - мультимедиа, периферия отдельные переговорные комнаты",
                date = null,
                time = null,
                address = "ул. Ленина, 20"
            )
        )
        roomMap.put(
            2,
            RoomUiState(
                id = 2,
                name = "Коворкинг студия",
                title = "Место твоего успеха",
                like = false,
                coast = 1500f,
                wifi = true,
                display = true,
                laptop = false,
                projector = true,
                printer = true,
                photoUri = listOf(
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room21),
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room22),
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room23),
                ),
                room = false,
                description = "Студия для удаленщиков и тех кто хочет сменить обстановку, есть все необходимое для работы - мультимедиа, периферия отдельные переговорные комнаты",
                date = null,
                time = null,
                address = "ул.  Карла маркса, 190/1"
            )
        )
        roomMap.put(
            3,
            RoomUiState(
                id = 3,
                name = "Ренесанс",
                title = "Твой коворкинг",
                like = false,
                coast = 200f,
                wifi = true,
                display = true,
                laptop = false,
                projector = true,
                printer = true,
                photoUri = listOf(
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room31),
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room32),
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room33),
                ),
                room = false,
                description = "Студия для удаленщиков и тех кто хочет сменить обстановку, есть все необходимое для работы - мультимедиа, периферия отдельные переговорные комнаты",
                date = null,
                time = null,
                address = "ул.  Карла маркса, 10/1"
            )
        )


        roomMap.put(
            4,
            RoomUiState(
                id = 4,
                name = "Апгрейд",
                title = "Лучшие коворкинги твоего города",
                like = false,
                coast = 4000f,
                wifi = true,
                display = true,
                laptop = false,
                projector = true,
                printer = true,
                photoUri = listOf(
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room42),
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room41),
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room43),
                ),
                room = false,
                description = "Студия для удаленщиков и тех кто хочет сменить обстановку, есть все необходимое для работы - мультимедиа, периферия отдельные переговорные комнаты",
                date = null,
                time = null,
                address = "пр. Мира, 43/4"
            )
        )



        roomMap.put(
            5,
            RoomUiState(
                id = 5,
                name = "Уолл Стрит",
                title = "Популярная сеть коворкинг пространств",
                like = false,
                coast = 3000f,
                wifi = true,
                display = true,
                laptop = false,
                projector = true,
                printer = true,
                photoUri = listOf(
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room51),
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room52),
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room53),
                ),
                room = false,
                description = "Студия для удаленщиков и тех кто хочет сменить обстановку, есть все необходимое для работы - мультимедиа, периферия отдельные переговорные комнаты",
                date = null,
                time = null,
                address = "ул. Иванова, 10"
            )
        )

        roomMap.put(
            6,
            RoomUiState(
                id = 6,
                name = "Баланс",
                title = "Коворкинг с кухней и обслуживанием",
                like = false,
                coast = 5000f,
                wifi = true,
                display = true,
                laptop = false,
                projector = true,
                printer = true,
                photoUri = listOf(
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room61),
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room62),
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room63),
                ),
                room = false,
                description = "Студия для удаленщиков и тех кто хочет сменить обстановку, есть все необходимое для работы - мультимедиа, периферия отдельные переговорные комнаты",
                date = null,
                time = null,
                address = "ул.  Карла маркса, 190/1"
            )
        )

        roomMap.put(
            7,
            RoomUiState(
                id = 7,
                name = "Территория",
                title = "Бизнес центр, аренда отдельных помещений",
                like = false,
                coast = 10000f,
                wifi = true,
                display = true,
                laptop = true,
                projector = true,
                printer = true,
                photoUri = listOf(
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room71),
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room72),
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room73),
                ),
                room = true,
                description = "Студия для удаленщиков и тех кто хочет сменить обстановку, есть все необходимое для работы - мультимедиа, периферия отдельные переговорные комнаты",
                date = null,
                time = null,
                address = "ул.  Пушкина, 40"
            )
        )

        roomMap.put(
            8,
            RoomUiState(
                id = 8,
                name = "Атлас",
                title = "Современный коворкинг центр",
                like = false,
                coast = 4000f,
                wifi = true,
                display = true,
                laptop = true,
                projector = true,
                printer = true,
                photoUri = listOf(
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room81),
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room82),
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room83),
                ),
                room = true,
                description = "Студия для удаленщиков и тех кто хочет сменить обстановку, есть все необходимое для работы - мультимедиа, периферия отдельные переговорные комнаты",
                date = null,
                time = null,
                address = "ул. Карла маркса, 52"
            )
        )

        roomMap.put(
            9,
            RoomUiState(
                id = 9,
                name = "Nice day",
                title = "Рабочее пространство",
                like = false,
                coast = 1500f,
                wifi = true,
                display = true,
                laptop = false,
                projector = true,
                printer = true,
                photoUri = listOf(
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room91),
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room92),
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room93),
                ),
                room = false,
                description = "Студия для удаленщиков и тех кто хочет сменить обстановку, есть все необходимое для работы - мультимедиа, периферия отдельные переговорные комнаты",
                date = null,
                time = null,
                address = "ул.  Карла маркса, 4"
            )
        )

        roomMap.put(
            10,
            RoomUiState(
                id = 10,
                name = "Академия",
                title = "Место деловых встреч",
                like = false,
                coast = 2000f,
                wifi = true,
                display = true,
                laptop = true,
                projector = true,
                printer = true,
                photoUri = listOf(
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room101),
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room102),
                    Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room103),
                ),
                room = true,
                description = "Студия для удаленщиков и тех кто хочет сменить обстановку, есть все необходимое для работы - мультимедиа, периферия отдельные переговорные комнаты",
                date = null,
                time = null,
                address = "ул. Борисова, 40"
            )
        )

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
    ): List<RoomUiState> {
        val room = roomList[id]
        room?.let {
            historyList.add(it.copy(date = date, time = time))
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
            reservedList.add(it.copy(date = date, time = time))
        }
        return reservedList
    }

    override suspend fun removeRoomFromReserved(id: Int): List<RoomUiState> {
        reservedList.removeIf { it.id == id }
        return reservedList
    }


}