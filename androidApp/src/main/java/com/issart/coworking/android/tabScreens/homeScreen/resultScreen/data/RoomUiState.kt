package com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data

import android.net.Uri
import androidx.core.net.toUri

data class RoomUiState(
    val name:String = "",
    val title:String = "",
    val like:Boolean = false,
    val coast:Float = 0f,
    val wifi:Boolean = false,
    val display:Boolean = false,
    val laptop:Boolean = false,
    val projector:Boolean = false,
    val printer:Boolean = false,
    val photoUri: Uri = "".toUri()
)
