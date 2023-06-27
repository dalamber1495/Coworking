package com.issart.coworking.android.tabScreens.homeScreen.reservedScreen.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.issart.coworking.android.ui.activeContentColor
import com.issart.coworking.android.ui.backgroundColor
import com.issart.coworking.android.ui.buttonTextStyle
import com.issart.coworking.android.ui.nameItemTextStyle

@Composable
fun ReservedScreenDialog(cancelReservedCallback: () -> Unit, backCallback: () -> Unit) {
    Dialog(onDismissRequest = backCallback) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(24.dp))
                .background(color = backgroundColor)
                .padding(horizontal = 12.dp), contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(64.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Отменить бронирование комнаты?",
                    style = nameItemTextStyle,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(50.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 9.dp)
                        .height(50.dp)
                        .indication(remember { MutableInteractionSource() }, null),
                    onClick = cancelReservedCallback,
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
                Spacer(modifier = Modifier.height(28.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 9.dp)
                        .height(50.dp)
                        .indication(remember { MutableInteractionSource() }, null),
                    onClick = backCallback,
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = activeContentColor,
                        contentColor = backgroundColor,
                    ),
                ) {
                    Text(text = "НАЗАД", style = buttonTextStyle)
                }
                Spacer(modifier = Modifier.height(32.dp))


            }

        }

    }
}