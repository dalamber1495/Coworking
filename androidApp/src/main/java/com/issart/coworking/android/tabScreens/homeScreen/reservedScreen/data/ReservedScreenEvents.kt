package com.issart.coworking.android.tabScreens.homeScreen.reservedScreen.data

sealed class ReservedScreenEvents {

    class GetRoomState(val id:Int):ReservedScreenEvents()
    class RemoveRoomFromReserved(val id:Int):ReservedScreenEvents()
}
