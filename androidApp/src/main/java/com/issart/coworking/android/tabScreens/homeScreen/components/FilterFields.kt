package com.issart.coworking.android.tabScreens.homeScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.issart.coworking.android.R
import com.issart.coworking.android.authScreen.components.CoworkingTextField
import com.issart.coworking.android.tabScreens.homeScreen.navigation.rootObject.HomeScreens
import com.issart.coworking.android.ui.activeContentColor
import com.issart.coworking.android.ui.backgroundFieldColor
import com.issart.coworking.android.ui.inactiveContentColor
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterFields(
    dateText: String,
    calendarStates: UseCaseState,
    timeStartText: String,
    timeEndText: String,
    clockStartStates: UseCaseState,
    clockEndStates: UseCaseState,
    numberPickerShow: MutableState<Boolean>,
    peopleText: String,
    navController: NavHostController,
    geoCodeText: String,
    singleRoomButton: () -> Unit,
    multimediaButton: () -> Unit,
    singleRoom: Boolean,
    multimedia: Boolean,
    selectedDate: LocalDate,
    setDateCallback: (LocalDate) -> Unit,
    setNumberPeopleCallback: (Int) -> Unit,
    numberPeople: Int
) {


    Spacer(modifier = Modifier.height(20.dp))
    CoworkingTextField(
        modifier = Modifier
            .fillMaxWidth(),
        readOnly = true,
        placeHolder = "Дата",
        text = dateText,
        valueCallback = {},
        leadingIcon = R.drawable.ic_calendar,
        onClick = {
            calendarStates.show()
        }
    )
    Spacer(modifier = Modifier.height(20.dp))
    Row {
        CoworkingTextField(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(end = 8.dp),
            placeHolder = "Время",
            readOnly = true,
            text = "С ${timeStartText}",
            valueCallback = {},
            leadingIcon = R.drawable.ic_clock,
            onClick = {
                clockStartStates.show()
            }
        )
        CoworkingTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
            placeHolder = "Время",
            readOnly = true,
            text = "До ${timeEndText}",
            valueCallback = {},
            leadingIcon = R.drawable.ic_clock,
            onClick = {
                clockEndStates.show()
            }
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
    CoworkingTextField(
        modifier = Modifier.fillMaxWidth(),
        placeHolder = "Кол-во человек",
        readOnly = true,
        text = "Кол-во человек: $peopleText",
        valueCallback = {},
        leadingIcon = R.drawable.ic_people,
        onClick = {
            numberPickerShow.value = true
        }
    )
    Spacer(modifier = Modifier.height(20.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CoworkingTextField(
            modifier = Modifier
                .height(72.dp)
                .weight(0.5f),
            placeHolder = stringResource(R.string.place_button),
            readOnly = true,
            text = geoCodeText,
            valueCallback = {},
            leadingIcon = R.drawable.ic_pin,
            onClick = {
                navController.navigate(HomeScreens.MapScreenRoute.route)
            }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .size(height = 52.dp, width = 64.dp)
                .background(
                    color = backgroundFieldColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable(
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    indication = null,
                    onClick = singleRoomButton
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_door),
                contentDescription = null,
                tint = if (singleRoom) activeContentColor else inactiveContentColor
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .size(height = 52.dp, width = 64.dp)
                .background(
                    color = backgroundFieldColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable(
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    indication = null,
                    onClick = multimediaButton
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_monitor),
                contentDescription = null,
                tint = if (multimedia) activeContentColor else inactiveContentColor
            )
        }

    }

    val timeBoundary = LocalDate.now().let { now -> now..now.plusYears(2) }

    CalendarDialog(
        state = calendarStates,
        config = CalendarConfig(
            yearSelection = true,
            monthSelection = true,
            style = CalendarStyle.MONTH,
            boundary = timeBoundary
        ),
        selection = CalendarSelection.Date(selectedDate = selectedDate) { newDate ->
            setDateCallback.invoke(newDate)
        }
    )

    if (numberPickerShow.value)
        NumberPickerDialog(
            onDismissRequest = { numberPickerShow.value = false },
            value = numberPeople,
            onEvent = setNumberPeopleCallback
        )
}