package com.issart.coworking.android.tabScreens.homeScreen.searchScreen.view

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.issart.coworking.android.MyApplicationTheme
import com.issart.coworking.android.authScreen.components.CoworkingTextField
import com.issart.coworking.android.tabScreens.homeScreen.navigation.graph.HomeGraph
import com.issart.coworking.android.tabScreens.homeScreen.navigation.rootObject.HomeScreens
import com.issart.coworking.android.tabScreens.homeScreen.searchScreen.model.SearchScreenUiEvents
import com.issart.coworking.android.tabScreens.homeScreen.searchScreen.viewModel.SearchScreenViewModel
import com.issart.coworking.android.ui.*
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {

    HomeGraph(navController = navController)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
    viewModel: SearchScreenViewModel = koinViewModel()
) {

    val state = viewModel.state.collectAsState()

    val calendarStates = rememberUseCaseState()
    val clockStartStates = rememberUseCaseState()
    val clockEndStates = rememberUseCaseState()
    ClockDialog(
        state = clockStartStates,
        selection = ClockSelection.HoursMinutes { hours, minutes ->
            viewModel.onEvent(SearchScreenUiEvents.SetTimeStartPicker( LocalTime.of(hours, minutes, 0)))
        },
        config = ClockConfig(
            defaultTime = state.value.timeStart,
            is24HourFormat = true
        )
    )
    ClockDialog(
        state = clockEndStates,
        selection = ClockSelection.HoursMinutes { hours, minutes ->
            viewModel.onEvent(SearchScreenUiEvents.SetTimeEndPicker( LocalTime.of(hours, minutes, 0)))
        },
        config = ClockConfig(
            defaultTime = state.value.timeEnd,
            is24HourFormat = true
        )
    )
    Box(contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp), contentAlignment = Alignment.Center
            ) {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((maxWidth.value * 0.82).dp),
                    model = com.issart.coworking.android.R.drawable.room,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                ) {
                    SubcomposeAsyncImageContent()
                    Box(
                        Modifier
                            .matchParentSize()
                            .background(color = backgroundColor.copy(alpha = 0.8f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Встречи и \nпрезентации  ", style = descriptionTextStyle)

                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                CoworkingTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    readOnly = true,
                    placeHolder = "Дата",
                    text = state.value.date.format(DateTimeFormatter.ofPattern("EEE, d MMM. yyyy")),
                    valueCallback = {},
                    leadingIcon = com.issart.coworking.android.R.drawable.ic_calendar,
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
                        text = state.value.timeStart.toString(),
                        valueCallback = {},
                        leadingIcon = com.issart.coworking.android.R.drawable.ic_clock,
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
                        text = state.value.timeEnd.toString(),
                        valueCallback = {},
                        leadingIcon = com.issart.coworking.android.R.drawable.ic_clock,
                        onClick = {
                            clockEndStates.show()
                        }
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                CoworkingTextField(
                    modifier = Modifier.fillMaxWidth(),
                    placeHolder = "Кол-во человек",
                    valueCallback = {},
                    leadingIcon = com.issart.coworking.android.R.drawable.ic_people
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
                        placeHolder = "Место",
                        valueCallback = {},
                        leadingIcon = com.issart.coworking.android.R.drawable.ic_pin
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(
                        modifier = Modifier
                            .size(height = 52.dp, width = 64.dp)
                            .background(
                                color = backgroundFieldColor,
                                shape = RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = com.issart.coworking.android.R.drawable.ic_door),
                            contentDescription = null,
                            tint = activeContentColor
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(
                        modifier = Modifier
                            .size(height = 52.dp, width = 64.dp)
                            .background(
                                color = backgroundFieldColor,
                                shape = RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = com.issart.coworking.android.R.drawable.ic_monitor),
                            contentDescription = null,
                            tint = activeContentColor
                        )
                    }
                }

                Spacer(modifier = Modifier.height(39.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .indication(remember { MutableInteractionSource() }, null),
                    onClick = {
                        navController.navigate(
                            HomeScreens.ResultScreenRoute.route
                        )
                    },
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = activeContentColor,
                        contentColor = backgroundColor
                    )
                ) {
                    Text(text = "НАЙТИ", style = buttonTextStyle)
                }
                Spacer(modifier = Modifier.height(116.dp))

            }


        }

            CalendarDialog(
                state = calendarStates,
                config = CalendarConfig(
                    yearSelection = true,
                    monthSelection = true,
                    style = CalendarStyle.MONTH,
                ),
                selection = CalendarSelection.Date(selectedDate = state.value.date) { newDate ->
                    viewModel.onEvent(SearchScreenUiEvents.SetDatePicker(newDate))
                }
            )

    }

}