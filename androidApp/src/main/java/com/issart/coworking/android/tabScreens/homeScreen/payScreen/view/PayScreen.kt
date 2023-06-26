package com.issart.coworking.android.tabScreens.homeScreen.payScreen.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.issart.coworking.android.R
import com.issart.coworking.android.tabScreens.homeScreen.navigation.rootObject.HomeScreens
import com.issart.coworking.android.tabScreens.homeScreen.payScreen.components.ClickableTextField
import com.issart.coworking.android.ui.*

@Composable
fun PayScreen(navController: NavHostController, id:Int?) {


    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 25.dp),
                horizontalArrangement = Arrangement.Start,
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
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                text = "Выберите способ оплаты",
                color = fontDescriptionColor,
                fontFamily = playfaird,
                fontSize = 24.sp,
                fontWeight = FontWeight.W400
            )
            Spacer(modifier = Modifier.height(24.dp))

            ClickableTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                text = "4321 **** **** ****",
                iconResourse = R.drawable.credit_card
            )
            Spacer(modifier = Modifier.height(16.dp))
            ClickableTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                text = "Наличными или картой в офисе",
                iconResourse = R.drawable.ic_home_tab,
                contentColor = activeContentColor
            )
        }
        Column(Modifier.fillMaxWidth().align(Alignment.BottomCenter)) {

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
                    .height(50.dp)
                    .indication(remember { MutableInteractionSource() }, null),
                onClick = {
                    navController.navigate(HomeScreens.ReservedScreenRoute.createRoute(id!!),HomeScreens.ReservedScreenRoute.options)
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
}