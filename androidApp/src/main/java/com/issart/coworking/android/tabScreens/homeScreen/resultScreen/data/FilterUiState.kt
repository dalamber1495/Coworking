package com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data

import java.time.OffsetDateTime

data class FilterUiState (
    val dateFilter: OffsetDateTime? = null,
    val timeFilter: Pair<OffsetDateTime, OffsetDateTime>? = null,
    val peopleFilter: Int? = null,
    val roomFilter: Boolean? = null,
    val multimediaFilter: Boolean? = null
)