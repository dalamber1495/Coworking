package com.issart.coworking.android.tabScreens.homeScreen.resultScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.issart.coworking.android.ui.*

@Composable
fun FilterField(text: String, closeCallback: () -> Unit) {

    Column {
        Row(
            modifier = Modifier
                .height(30.dp)
                .padding()
                .wrapContentWidth()
        ) {
            Box(
                modifier = Modifier
                    .height(30.dp)
                    .wrapContentWidth()
                    .background(
                        color = backgroundFieldColor,
                        shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)
                    ), contentAlignment = Alignment.Center
            ) {

                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    style = filterTextStyle,
                    text = text
                )

            }
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(
                        color = backgroundColor,
                        shape = RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp)
                    )
                    .border(
                        1.dp,
                        backgroundFieldColor,
                        RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp)
                    )
                    .clickable(onClick = closeCallback),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = inactiveContentColor
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Preview
@Composable
fun previewFun() {
    FilterField(text = " rgrdg") {

    }
}