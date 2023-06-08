package com.issart.coworking.android.domain.repositories.local.useCases

import com.issart.coworking.android.domain.repositories.local.getRoomList.RoomRepository
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.RoomUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRoomByIdUseCase(
    private val roomRepository: RoomRepository
) {
    fun invoke(id: Int): Flow<RoomUiState> {
        return flow {  emit(roomRepository.getRoomById(id)!!)}
    }
}