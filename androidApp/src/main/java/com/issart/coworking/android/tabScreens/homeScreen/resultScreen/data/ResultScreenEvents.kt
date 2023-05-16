package com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data

sealed class ResultScreenEvents{
    class SetFilter(val filter:FilterUiState):ResultScreenEvents()
}
