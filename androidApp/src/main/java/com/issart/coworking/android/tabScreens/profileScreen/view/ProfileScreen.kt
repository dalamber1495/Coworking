package com.issart.coworking.android.tabScreens.profileScreen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.issart.coworking.android.R
import com.issart.coworking.android.tabScreens.homeScreen.payScreen.components.ClickableTextField
import com.issart.coworking.android.tabScreens.profileScreen.components.RoomCard
import com.issart.coworking.android.tabScreens.profileScreen.viewModel.ProfileScreenViewModel
import com.issart.coworking.android.ui.backgroundColor
import com.issart.coworking.android.ui.descriptionTextStyle
import com.issart.coworking.android.ui.nameItemTextStyle
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(profileScreenViewModel: ProfileScreenViewModel = koinViewModel()) {


    val historyState = profileScreenViewModel.historyListState.collectAsState()
    val reservedState = profileScreenViewModel.reservedListState.collectAsState()

    Box(contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .padding(horizontal = 25.dp)
                .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((maxWidth.value * 0.5).dp),
                    model = R.drawable.room,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                ) {
                    SubcomposeAsyncImageContent()
                    Box(
                        Modifier
                            .matchParentSize()
                            .background(color = backgroundColor.copy(alpha = 0.8f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 32.dp) , text = "Профиль", style = descriptionTextStyle)

                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            ClickableTextField(modifier = Modifier
                .fillMaxWidth()
                .height(52.dp), iconResourse = R.drawable.ic_email, text = "mikhail.nekrasov@gmail.com")
            Spacer(modifier = Modifier.height(24.dp))
            Text(modifier = Modifier.fillMaxWidth(), text = "Текущие бронирования", style = nameItemTextStyle, fontSize = 24.sp)

            reservedState.value?.asReversed()?.forEach {
                Spacer(modifier = Modifier.height(16.dp))
                RoomCard(room = it)
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(modifier = Modifier.fillMaxWidth(), text = "История", style = nameItemTextStyle, fontSize = 24.sp)

            historyState.value?.asReversed()?.forEach{
                Spacer(modifier = Modifier.height(16.dp))
                RoomCard(room = it)
            }
            Spacer(modifier = Modifier.height(100.dp))



        }
    }


}