package com.issart.coworking.android.tabScreens.homeScreen.searchScreen.model

import java.time.LocalDate
import java.time.LocalTime

sealed class SearchScreenUiEvents{
    class SetDatePicker(val text:LocalDate):SearchScreenUiEvents()
    class SetTimeStartPicker(val text:LocalTime):SearchScreenUiEvents()
    class SetTimeEndPicker(val text:LocalTime):SearchScreenUiEvents()
    class SetPeoplePicker(val text:String):SearchScreenUiEvents()
    class SetLocate(val text:String):SearchScreenUiEvents()
    class SetRoom(val room:Boolean):SearchScreenUiEvents()
    class SetMultimedia(val multimedia:Boolean):SearchScreenUiEvents()
    object ClickFindButton : SearchScreenUiEvents()

}
