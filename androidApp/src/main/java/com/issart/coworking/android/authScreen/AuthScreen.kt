package com.issart.coworking.android.authScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.issart.coworking.android.authScreen.components.CoworkingTextField
import com.issart.coworking.android.navigation.routeObject.AppScreens
import com.issart.coworking.android.navigation.routeObject.routeGraph
import com.issart.coworking.android.ui.*

@Composable
fun AuthScreen(
    navHostController: NavHostController
) {


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 44.dp),
            text = "Добро \nпожаловать",
            fontSize = 40.sp,
            color = fontDescriptionColor,
            fontFamily = playfaird,
            fontWeight = FontWeight.W400,
            minLines = 2,
            textAlign = TextAlign.Start
        )

        Column(
            modifier = Modifier.width(323.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CoworkingTextField(
                modifier = Modifier,
                placeHolder = "Email",
                valueCallback = {},
                leadingIcon = com.issart.coworking.android.R.drawable.ic_email
            )
            CoworkingTextField(
                modifier = Modifier.width(323.dp),
                placeHolder = "Пароль",
                valueCallback = {},
                leadingIcon = com.issart.coworking.android.R.drawable.password
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier
                    .width(323.dp)
                    .height(50.dp)
                    .indication(remember { MutableInteractionSource() }, null),
                onClick = {
                    navHostController.navigate(
                        AppScreens.MainAppScreen.route,
                        NavOptions.Builder().setPopUpTo(routeGraph,true).build())
                },
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = activeContentColor,
                    contentColor = backgroundColor
                )
            ) {
                Text(text = "ВОЙТИ", style = buttonTextStyle)
            }
        }
        Button(
            modifier = Modifier
                .width(323.dp)
                .height(50.dp)
                .indication(remember { MutableInteractionSource() }, null),
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = backgroundColor,
                contentColor = activeContentColor
            ),
            border = BorderStroke(1.dp, color = activeContentColor)
        ) {
            Text(text = "ЗАРЕГИСТРИРОВАТЬСЯ", style = buttonTextStyle, color = activeContentColor)
        }

        Row(
            modifier = Modifier.width(323.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Войти с помощью",
                fontSize = 16.sp,
                fontFamily = sourcesanspro,
                fontWeight = FontWeight.W400,
                color = fontDescriptionColor
            )
            Image(
                painter = painterResource(id = com.issart.coworking.android.R.drawable.login_with_facebook_button),
                contentDescription = null
            )
            Image(
                painter = painterResource(id = com.issart.coworking.android.R.drawable.login_with_google_button__1_),
                contentDescription = null
            )
        }

    }


}