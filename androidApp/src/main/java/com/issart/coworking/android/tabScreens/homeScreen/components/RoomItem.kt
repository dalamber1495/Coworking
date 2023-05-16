package com.issart.coworking.android.tabScreens.homeScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.RoomUiState
import com.issart.coworking.android.ui.*


@Composable
fun RoomItem(modifier: Modifier = Modifier, room: RoomUiState) {


        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = backgroundFieldColor, shape = RoundedCornerShape(12.dp))
        ) {
            Card(modifier = Modifier.size(maxWidth), shape = RoundedCornerShape(12.dp)) {
                AsyncImage(
                    model = room.photoUri,
                    modifier = Modifier
                        .size(maxWidth)
                        .background(
                            color = backgroundFieldColor,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        Text(modifier = Modifier.fillMaxWidth(), text = room.name, style = nameItemTextStyle)
        Text(modifier = Modifier.fillMaxWidth(), text = room.title, style = titleItemTextStyle)
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            if (room.wifi) {
                Icon(
                    painter = painterResource(id = com.issart.coworking.android.R.drawable.wifi),
                    contentDescription = null,
                    tint = inactiveContentColor
                )
            }
            if (room.display) {
                Icon(
                    painter = painterResource(id = com.issart.coworking.android.R.drawable.monitor),
                    contentDescription = null,
                    tint = inactiveContentColor
                )
            }
            if (room.laptop) {
                Icon(
                    painter = painterResource(id = com.issart.coworking.android.R.drawable.laptop),
                    contentDescription = null,
                    tint = inactiveContentColor
                )
            }
            if (room.projector) {
                Icon(
                    painter = painterResource(id = com.issart.coworking.android.R.drawable.video),
                    contentDescription = null,
                    tint = inactiveContentColor
                )
            }
            if (room.printer) {
                Icon(
                    painter = painterResource(id = com.issart.coworking.android.R.drawable.printer),
                    contentDescription = null,
                    tint = inactiveContentColor
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "${room.coast.toInt()} ₽", style = coastItemTextStyle)
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = com.issart.coworking.android.R.drawable.ic_order_tab),
                contentDescription = null,
                tint = activeContentColor
            )
        }


}