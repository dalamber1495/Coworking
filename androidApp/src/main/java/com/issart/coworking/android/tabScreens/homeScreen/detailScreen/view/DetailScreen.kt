package com.issart.coworking.android.tabScreens.homeScreen.detailScreen

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
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
import com.issart.coworking.android.R
import com.issart.coworking.android.authScreen.components.CoworkingTextField
import com.issart.coworking.android.tabScreens.homeScreen.detailScreen.data.DetailScreenEvents
import com.issart.coworking.android.tabScreens.homeScreen.detailScreen.data.RoomDetailUiState
import com.issart.coworking.android.tabScreens.homeScreen.navigation.rootObject.HomeScreens
import com.issart.coworking.android.ui.*
import kotlinx.coroutines.flow.StateFlow
import java.time.format.DateTimeFormatter

@Composable
fun DetailScreen(
    navController: NavHostController,
    stateFlow: StateFlow<RoomDetailUiState>,
    onEvent: (DetailScreenEvents) -> Unit,
    id: Int?
) {

    val state = stateFlow.collectAsState()

    LaunchedEffect(key1 = id) {
        id?.let {
            onEvent.invoke(DetailScreenEvents.GetRoomState(id))
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
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(modifier = Modifier.clickable {
                    navController.popBackStack()
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
                Icon(
                    imageVector = ImageVector.vectorResource(id = if (!state.value.like) R.drawable.heart else R.drawable.heart_fill),
                    contentDescription = null,
                    tint = activeContentColor
                )
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, start = 25.dp, end = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = state.value.name, style = nameItemTextStyle, fontSize = 24.sp)
            Text(
                text = "${state.value.coast.toInt()} ₽",
                style = coastItemTextStyle,
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.height(1.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            text = state.value.title,
            style = titleItemTextStyle
        )
        Spacer(modifier = Modifier.height(9.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            text = state.value.description,
            style = nameItemTextStyle,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(18.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconItem(resId = R.drawable.wifi, text = "Wi-Fi")
            IconItem(resId = R.drawable.monitor, text = "Экран")
            IconItem(resId = R.drawable.laptop, text = "Ноутбук")
            IconItem(resId = R.drawable.video, text = "Проектор")
            IconItem(resId = R.drawable.printer, text = "Принтер")
        }
        Spacer(modifier = Modifier.height(16.dp))
        CoworkingTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            placeHolder = "Дата",
            readOnly = true,
            valueCallback = {},
            text = state.value.date?.format(DateTimeFormatter.ofPattern("EEE, dd MMM. yyyy")) ?: "",
            leadingIcon = R.drawable.ic_calendar
        )
        Spacer(modifier = Modifier.height(20.dp))
        CoworkingTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            text = "${state.value.time?.first?.format(DateTimeFormatter.ofPattern("HH:mm"))}-${
                state.value.time?.second?.format(
                    DateTimeFormatter.ofPattern("HH:mm")
                )
            }",
            readOnly = true,
            placeHolder = "Время",
            valueCallback = {},
            leadingIcon = R.drawable.ic_clock
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
                .height(50.dp)
                .indication(remember { MutableInteractionSource() }, null),
            onClick = {
                navController.navigate(
                    HomeScreens.PayScreenRoute.route
                )
            },
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = activeContentColor,
                contentColor = backgroundColor
            )
        ) {
            Text(text = "ЗАБРОНИРОВАТЬ", style = buttonTextStyle)
        }
        Spacer(modifier = Modifier.height(100.dp))


    }
}


@Composable
fun IconItem(resId: Int, text: String) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            imageVector = ImageVector.vectorResource(id = resId),
            contentDescription = null,
            tint = inactiveContentColor
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text, style = titleItemTextStyle)
    }

}