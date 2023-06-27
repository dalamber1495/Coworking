package com.issart.coworking.android.tabScreens.homeScreen.reservedScreen.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.issart.coworking.android.tabScreens.homeScreen.navigation.rootObject.HomeScreens
import com.issart.coworking.android.tabScreens.homeScreen.reservedScreen.data.ReservedScreenEvents
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.RoomUiState
import com.issart.coworking.android.ui.*
import kotlinx.coroutines.flow.StateFlow
import java.time.format.DateTimeFormatter

@Composable
fun ReservedScreen(
    navController: NavHostController,
    stateFlow: StateFlow<RoomUiState>,
    onEvent: (ReservedScreenEvents) -> Unit,
    id: Int?
) {


    val state = stateFlow.collectAsState()
    val showDialog = remember {
        mutableStateOf(false)
    }
    BackHandler(enabled = true) {

        navController.popBackStack(HomeScreens.SearchScreenRoute.route,false)

    }
    LaunchedEffect(key1 = id) {
        id?.let {
            onEvent.invoke(ReservedScreenEvents.GetRoomState(id))
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 25.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(modifier = Modifier.clickable {
                    navController.popBackStack(HomeScreens.SearchScreenRoute.route,inclusive = false)
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = fontDescriptionColor
                    )
                    Spacer(Modifier.width(2.dp))
                    Text(
                        text = "Назад",
                        fontSize = 16.sp,
                        fontFamily = sourcesanspro,
                        fontWeight = FontWeight.W400,
                        color = fontDescriptionColor
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(top = 8.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.width(9.dp))
            state.value.photoUri.forEachIndexed { index, uri ->
                Card(modifier = Modifier.wrapContentSize(), shape = RoundedCornerShape(12.dp)) {
                    AsyncImage(
                        modifier = Modifier
                            .size(width = 238.dp, height = 210.dp),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(uri)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
                if (index == state.value.photoUri.size - 1)
                    Spacer(modifier = Modifier.width(9.dp))
            }
        }

        Spacer(Modifier.height(40.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${state.value.name} успешно\nзабронирована",
                style = nameItemTextStyle,
                fontSize = 24.sp
            )

        }
        Spacer(Modifier.height(33.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
        ) {

            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = ImageVector.vectorResource(id = com.issart.coworking.android.R.drawable.ic_calendar),
                    contentDescription = null,
                    tint = fontDescriptionColor
                )
                Spacer(modifier = Modifier.width(13.dp))
                Column {
                    Text(
                        text = state.value.date?.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                            ?: "",
                        style = nameItemTextStyle
                    )
                    Text(
                        text = "c ${state.value.time?.first?.format(DateTimeFormatter.ofPattern("HH:mm"))} до ${
                            state.value.time?.second?.format(
                                DateTimeFormatter.ofPattern("HH:mm")
                            )
                        }",
                        style = nameItemTextStyle
                    )
                }
            }
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = ImageVector.vectorResource(id = com.issart.coworking.android.R.drawable.ic_pin),
                    contentDescription = null,
                    tint = fontDescriptionColor
                )
                Spacer(modifier = Modifier.width(13.dp))
                Column {
                    Text(
                        text = state.value.name,
                        style = nameItemTextStyle,
                        fontSize = 16.sp
                    )
                    Text(
                        text = state.value.address,
                        style = nameItemTextStyle,
                        fontSize = 16.sp
                    )
                }
            }

        }

    }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
                .height(50.dp)
                .indication(remember { MutableInteractionSource() }, null),
            onClick = {
                showDialog.value = true
            },
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = backgroundColor,
                contentColor = activeContentColor,
            ),
            border = BorderStroke(1.dp, activeContentColor)
        ) {
            Text(
                text = "ОТМЕНИТЬ БРОНИРОВАНИЕ",
                style = buttonTextStyle,
                color = activeContentColor
            )
        }
        Spacer(modifier = Modifier.height(100.dp))

    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {


        if (showDialog.value)
            ReservedScreenDialog(cancelReservedCallback = {
                navController.getBackStackEntry(HomeScreens.DetailScreenRoute.route).savedStateHandle["cancelReserve"] =
                    true
                navController.popBackStack(HomeScreens.DetailScreenRoute.route,false, saveState = true)
            }) {
                showDialog.value = false
            }
    }

}