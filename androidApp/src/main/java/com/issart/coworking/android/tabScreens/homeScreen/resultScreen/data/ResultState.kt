package com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data

data class ResultState(
    val rooms:List<RoomUiState> = emptyList(),
    val filters:FilterUiState = FilterUiState()
)
