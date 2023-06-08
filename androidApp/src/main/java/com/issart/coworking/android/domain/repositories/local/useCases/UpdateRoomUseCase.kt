package com.issart.coworking.android.domain.repositories.local.useCases

import com.issart.coworking.android.domain.repositories.local.getRoomList.RoomRepository
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.RoomUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdateRoomUseCase(
    private val roomRepository: RoomRepository
) {
    fun invoke(room:RoomUiState): Flow<RoomUiState> {
        return flow {  emit(roomRepository.updateRoom(room))}
    }
}