package com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data

import java.time.LocalDate
import java.time.LocalTime

sealed class ResultScreenEvents{
    class SetFilter(val filter:FilterUiState):ResultScreenEvents()

    class SetDatePicker(val text: LocalDate): ResultScreenEvents()
    class SetTimeStartPicker(val text: LocalTime): ResultScreenEvents()
    class SetTimeEndPicker(val text: LocalTime): ResultScreenEvents()
    class SetPeoplePicker(val text:Int): ResultScreenEvents()
    class SetLocate(val text:String): ResultScreenEvents()
    object SetRoom: ResultScreenEvents()
    object SetMultimedia: ResultScreenEvents()
    object ApplyFilters:ResultScreenEvents()

}
