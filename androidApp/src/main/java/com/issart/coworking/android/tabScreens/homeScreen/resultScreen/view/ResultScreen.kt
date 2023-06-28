import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavHostController
import com.issart.coworking.android.tabScreens.homeScreen.components.RoomItem
import com.issart.coworking.android.tabScreens.homeScreen.navigation.rootObject.HomeScreens
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.components.BottomFilterSheet
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.components.FilterField
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.FilterUiState
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.ResultScreenEvents
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.ResultState
import com.issart.coworking.android.ui.backgroundColor
import com.issart.coworking.android.ui.fontDescriptionColor
import com.issart.coworking.android.ui.inactiveContentColor
import com.issart.coworking.android.ui.sourcesanspro
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterialApi::class)
@Composable
fun ResultScreen(
    navController: NavHostController,
    stateFlow: StateFlow<ResultState>,
    onEvent: (ResultScreenEvents) -> Unit,
    filterBottomSheetState: StateFlow<FilterUiState>
) {


    val state = stateFlow.collectAsState()
    val filterSheetState = filterBottomSheetState.collectAsState()


    val lifecycle = remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    val scope = rememberCoroutineScope()
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle.value = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            scope.cancel()
        }
    }

    val sheetFilterState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldFilterState = rememberBottomSheetScaffoldState(bottomSheetState = sheetFilterState)
    BottomSheetScaffold(
        sheetContent = {
            BottomFilterSheet(filterSheetState, onEvent, navController) {
                scope.launch {
                    sheetFilterState.collapse()
                }
            }
        },
        scaffoldState = scaffoldFilterState,
        floatingActionButtonPosition = FabPosition.Center,
        sheetShape = RoundedCornerShape(topEnd = 0.dp, topStart = 0.dp),
        sheetBackgroundColor = Color.Unspecified,
        backgroundColor = backgroundColor,
        sheetElevation = 0.dp,
    ) {
        Column {
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
                            text = "Назад к поиску",
                            fontSize = 16.sp,
                            fontFamily = sourcesanspro,
                            fontWeight = FontWeight.W400,
                            color = fontDescriptionColor
                        )
                    }
                    Icon(
                        modifier = Modifier.clickable(interactionSource = remember {
                            MutableInteractionSource()
                        }, indication = null, onClick = {
                            scope.launch {
                                if (sheetFilterState.isCollapsed)
                                    sheetFilterState.expand()
                                else
                                    sheetFilterState.collapse()
                            }
                        }),
                        imageVector = ImageVector.vectorResource(id = com.issart.coworking.android.R.drawable.ic_filter),
                        contentDescription = null,
                        tint = inactiveContentColor
                    )
                }

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    state.value.filters.dateFilter?.let {
                        FilterField(text = it.format(DateTimeFormatter.ofPattern("EEE, dd MMM. yyyy"))) {
                            onEvent.invoke(
                                ResultScreenEvents.SetFilter(
                                    state.value.filters.copy(
                                        dateFilter = null
                                    )
                                )
                            )
                        }
//                Spacer(modifier = Modifier.width(12.dp))
                    }
                    state.value.filters.timeFilter?.let {
                        FilterField(
                            text = "${it.first.format(DateTimeFormatter.ofPattern("HH:mm"))}-${
                                it.second.format(
                                    DateTimeFormatter.ofPattern("HH:mm")
                                )
                            }"
                        ) {
                            onEvent.invoke(
                                ResultScreenEvents.SetFilter(
                                    state.value.filters.copy(
                                        timeFilter = null
                                    )
                                )
                            )
                        }
//                Spacer(modifier = Modifier.width(12.dp))

                    }
                    state.value.filters.peopleFilter?.let {
                        FilterField(text = "$it ч.") {
                            onEvent.invoke(
                                ResultScreenEvents.SetFilter(
                                    state.value.filters.copy(
                                        peopleFilter = null
                                    )
                                )
                            )
                        }
//                Spacer(modifier = Modifier.width(12.dp))

                    }
                    state.value.filters.roomFilter?.let {
                        if (it)
                            FilterField(text = "Отдельная комната") {
                                onEvent.invoke(
                                    ResultScreenEvents.SetFilter(
                                        state.value.filters.copy(
                                            roomFilter = null
                                        )
                                    )
                                )
                            }
//                Spacer(modifier = Modifier.width(12.dp))

                    }
                    state.value.filters.multimediaFilter?.let {
                        if (it)
                            FilterField(text = "Мультимедиа") {
                                onEvent.invoke(
                                    ResultScreenEvents.SetFilter(
                                        state.value.filters.copy(
                                            multimediaFilter = null
                                        )
                                    )
                                )
                            }
                    }
                }
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 18.dp)
            ) {
                items(
                    count = state.value.rooms.size,
                    key = { index -> state.value.rooms[index].id }) { index ->
                    Column(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)
                    ) {
                        RoomItem(
                            modifier = Modifier,
                            room = state.value.rooms[index],
                            { onEvent.invoke(ResultScreenEvents.SetLikeOnRoom(index)) }) {
                            onEvent.invoke(
                                ResultScreenEvents.AddRoomInHistory(
                                    state.value.rooms[index].id,
                                    state.value.rooms[index].date,
                                    state.value.rooms[index].time
                                )
                            )
                            navController.navigate(HomeScreens.DetailScreenRoute.createRoute(state.value.rooms[index].id))
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }

}