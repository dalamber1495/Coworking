package com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data

import android.net.Uri
import androidx.core.net.toUri
import java.time.LocalDate
import java.time.LocalTime

data class RoomUiState(
    val id:Int = -1,
    val name: String = "",
    val title: String = "",
    val description: String = "",
    val like: Boolean = false,
    val coast: Float = 0f,
    val date: LocalDate? = null,
    val time:Pair<LocalTime, LocalTime>? = null,
    val wifi: Boolean = false,
    val display: Boolean = false,
    val laptop: Boolean = false,
    val projector: Boolean = false,
    val printer: Boolean = false,
    val room: Boolean = false,
    val photoUri: List<Uri> = emptyList(),
    val address: String = ""
){
    fun doesMatchFilter(filter:FilterUiState):Boolean{
        if(filter.multimediaFilter == true){
            if(wifi||laptop||printer||projector || display == false)
                return false
        }
        if (filter.roomFilter == true)
            if (!room)
                return false
        return true
    }
}
