package com.issart.coworking.android.tabScreens.homeScreen.detailScreen.data

import android.net.Uri
import androidx.core.net.toUri
import java.time.LocalDate
import java.time.LocalTime
import java.time.OffsetDateTime

data class RoomDetailUiState(
    val name: String = "",
    val title: String = "",
    val description: String = "",
    val like: Boolean = false,
    val coast: Float = 0f,
    val date:LocalDate? = null,
    val time:Pair<LocalTime, LocalTime>? = null,
    val wifi: Boolean = false,
    val display: Boolean = false,
    val laptop: Boolean = false,
    val projector: Boolean = false,
    val printer: Boolean = false,
    val photoUri: List<Uri> = emptyList()

)
