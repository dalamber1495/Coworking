package com.issart.coworking.android.tabScreens.homeScreen.components

import android.graphics.drawable.ShapeDrawable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.RoomUiState
import com.issart.coworking.android.ui.*


@Composable
fun RoomItem(modifier: Modifier = Modifier, room: RoomUiState, onClickLike:()->Unit, onClick:()->Unit) {


    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundFieldColor, shape = RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
    ) {
        Card(modifier = Modifier.size(maxWidth), shape = RoundedCornerShape(12.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(room.photoUri)
                    .crossfade(true)
                    .build(),
                modifier = Modifier
                    .size(maxWidth)
                    .background(
                        color = backgroundFieldColor,
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Box(modifier = Modifier.matchParentSize(), contentAlignment = Alignment.TopEnd) {

                Icon(
                    painter =
                    painterResource(id = com.issart.coworking.android.R.drawable.heart),
                    modifier = Modifier.padding(end = 10.dp, top = 10.dp).size(23.dp)
                        .clickable(interactionSource = remember {
                            MutableInteractionSource()
                        }, indication = null, onClick = onClickLike),
                    contentDescription = null,
                    tint = activeContentColor
                )

            }
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