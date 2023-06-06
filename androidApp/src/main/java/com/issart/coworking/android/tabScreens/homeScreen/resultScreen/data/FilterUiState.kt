package com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data

import java.time.LocalDate
import java.time.LocalTime

data class FilterUiState (
    val dateFilter: LocalDate? = null,
    val timeFilter: Pair<LocalTime, LocalTime>? = null,
    val peopleFilter: Int? = null,
    val roomFilter: Boolean? = null,
    val multimediaFilter: Boolean? = null,
    val geoAddress:String? = null

)