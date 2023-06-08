package com.issart.coworking.android.tabScreens.homeScreen.detailScreen.data


sealed class DetailScreenEvents{
        class GetRoomState(val id: Int):DetailScreenEvents()
        class SetLikeOnRoom(val like:Boolean):DetailScreenEvents()

}
