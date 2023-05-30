package com.issart.coworking.android.tabScreens.homeScreen.detailScreen.data

import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.FilterUiState

sealed class DetailScreenEvents{
        class GetRoomState(val id: Int):DetailScreenEvents()

}
