package com.issart.coworking.android.tabScreens.homeScreen.resultScreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.issart.coworking.android.tabScreens.homeScreen.components.FilterFields
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.FilterUiState
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.ResultScreenEvents
import com.issart.coworking.android.ui.activeContentColor
import com.issart.coworking.android.ui.backgroundColor
import com.issart.coworking.android.ui.buttonTextStyle
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomFilterSheet(
    state: State<FilterUiState>,
    onEvent: (ResultScreenEvents) -> Unit,
    navController: NavHostController,
    closeSheet:()->Unit
) {


    val numberPickerShow = remember { mutableStateOf(false) }
    val calendarStates = rememberUseCaseState()
    val clockStartStates = rememberUseCaseState()
    val clockEndStates = rememberUseCaseState()
    ClockDialog(
        state = clockStartStates,
        selection = ClockSelection.HoursMinutes { hours, minutes ->
            onEvent(
                ResultScreenEvents.SetTimeStartPicker(
                    LocalTime.of(
                        hours,
                        minutes,
                        0
                    )
                )
            )
        },
        config = ClockConfig(
            defaultTime = state.value.timeFilter?.first?: LocalTime.of(LocalTime.now().hour+1,0),
            is24HourFormat = true
        )
    )
    ClockDialog(
        state = clockEndStates,
        selection = ClockSelection.HoursMinutes { hours, minutes ->
            onEvent(
                ResultScreenEvents.SetTimeEndPicker(
                    LocalTime.of(
                        hours,
                        minutes,
                        0
                    )
                )
            )
        },
        config = ClockConfig(
            defaultTime = state.value.timeFilter?.second?: LocalTime.of(LocalTime.now().hour+2,0),
            is24HourFormat = true
        )
    )

    Card(
        modifier = Modifier.wrapContentSize(),
        elevation = 2.dp,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        border = BorderStroke(1.dp, color = activeContentColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(backgroundColor)
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Divider(
                    modifier = Modifier.width(35.dp),
                    color = activeContentColor,
                    thickness = 3.dp
                )
                Spacer(modifier = Modifier.height(16.dp))
                FilterFields(
                    dateText = state.value.dateFilter?.format(DateTimeFormatter.ofPattern("EEE, d MMM. yyyy"))!!,
                    calendarStates = calendarStates,
                    timeStartText = state.value.timeFilter?.first?.format(
                        DateTimeFormatter.ofPattern("HH:mm"))!!,
                    timeEndText = state.value.timeFilter?.second?.format(
                        DateTimeFormatter.ofPattern("HH:mm"))!!,
                    clockStartStates = clockStartStates,
                    clockEndStates = clockEndStates,
                    numberPickerShow = numberPickerShow,
                    peopleText = state.value.peopleFilter.toString(),
                    navController = navController,
                    geoCodeText = state.value.geoAddress?:"Место",
                    singleRoomButton = { onEvent(ResultScreenEvents.SetRoom) },
                    multimediaButton = { onEvent(ResultScreenEvents.SetMultimedia) },
                    singleRoom = state.value.roomFilter?:false,
                    multimedia = state.value.multimediaFilter?:false,
                    selectedDate = state.value.dateFilter?: LocalDate.now(),
                    setDateCallback = {onEvent(ResultScreenEvents.SetDatePicker(it))},
                    setNumberPeopleCallback = {onEvent(ResultScreenEvents.SetPeoplePicker(it))},
                    numberPeople = state.value.peopleFilter?:1
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .indication(remember { MutableInteractionSource() }, null),
                    onClick = {
                        onEvent(ResultScreenEvents.ApplyFilters)
                        closeSheet.invoke()
                    },
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = activeContentColor,
                        contentColor = backgroundColor
                    )
                ) {
                    Text(text = "ПРИМЕНИТЬ", style = buttonTextStyle)
                }
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}