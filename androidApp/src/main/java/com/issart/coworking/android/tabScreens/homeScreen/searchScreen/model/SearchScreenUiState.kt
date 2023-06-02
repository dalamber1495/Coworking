package com.issart.coworking.android.tabScreens.homeScreen.searchScreen.model

import java.time.LocalDate
import java.time.LocalTime
import java.time.OffsetDateTime
import java.util.Date

data class SearchScreenUiState(
    val date: LocalDate = LocalDate.now(),
    val timeStart: LocalTime = LocalTime.of(LocalTime.now().hour.plus(1), 0),
    val timeEnd: LocalTime = LocalTime.of(LocalTime.now().hour.plus(3), 0),
    val people: String = "",
    val geoCoding: String = "",
    val room: Boolean = false,
    val multimedia: Boolean = false
)
