package com.issart.coworking.android.domain.repositories.local.getRoomList

import android.net.Uri
import com.issart.coworking.android.R
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.RoomUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRoomListUseCase() {


    fun invoke(): Flow<List<RoomUiState>> {

        return flow {
            val mutaList = mutableListOf<RoomUiState>()

            var id = 0
            repeat(20) {
                mutaList.add(
                    RoomUiState(
                        id = id,
                        name = "Room number 1",
                        title = "coworking Worki",
                        like = false,
                        coast = 2000f,
                        wifi = true,
                        display = true,
                        laptop = false,
                        projector = true,
                        printer = true,
                        photoUri = Uri.parse("android.resource://com.issart.coworking.android/" + R.drawable.room),
                        room = false
                    )
                )
                id++
            }
            emit(
                mutaList
            )
        }
    }
}