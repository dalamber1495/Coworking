package com.issart.coworking.android.tabScreens.homeScreen.searchScreen.view

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.issart.coworking.android.authScreen.components.CoworkingTextField
import com.issart.coworking.android.tabScreens.homeScreen.navigation.graph.HomeGraph
import com.issart.coworking.android.tabScreens.homeScreen.navigation.rootObject.HomeScreens
import com.issart.coworking.android.ui.*

@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {

    HomeGraph(navController = navController)

}

@Composable
fun SearchScreen(navController: NavHostController) {

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
            CoworkingTextField(
                modifier = Modifier.fillMaxWidth(),
                placeHolder = "Дата",
                valueCallback = {},
                leadingIcon = com.issart.coworking.android.R.drawable.ic_calendar
            )
            CoworkingTextField(
                modifier = Modifier.fillMaxWidth(),
                placeHolder = "Время",
                valueCallback = {},
                leadingIcon = com.issart.coworking.android.R.drawable.ic_clock
            )
            CoworkingTextField(
                modifier = Modifier.fillMaxWidth(),
                placeHolder = "Кол-во человек",
                valueCallback = {},
                leadingIcon = com.issart.coworking.android.R.drawable.ic_people
            )

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
                        .padding(top = 20.dp)
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
                        .padding(top = 20.dp)
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
                        HomeScreens.ResultScreenRoute.route)
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

}