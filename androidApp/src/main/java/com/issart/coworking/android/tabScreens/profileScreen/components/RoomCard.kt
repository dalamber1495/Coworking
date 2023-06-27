package com.issart.coworking.android.tabScreens.profileScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.RoomUiState
import com.issart.coworking.android.ui.*
import java.time.format.DateTimeFormatter

@Composable
fun RoomCard(
    modifier: Modifier = Modifier,
    room: RoomUiState,
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = backgroundFieldColor, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp), text = room.name, style = nameItemTextStyle
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {

                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = ImageVector.vectorResource(id = com.issart.coworking.android.R.drawable.ic_calendar),
                        contentDescription = null,
                        tint = fontDescriptionColor
                    )
                    Spacer(modifier = Modifier.width(13.dp))
                    Column {
                        androidx.compose.material.Text(
                            text = room.date?.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                                ?: "",
                            color = inactiveContentColor,
                            fontFamily = sourcesanspro,
                            fontWeight = FontWeight.W400,
                            fontSize = 16.sp
                        )
                        androidx.compose.material.Text(
                            text = "c ${room.time?.first?.format(DateTimeFormatter.ofPattern("HH:mm"))} до ${
                                room.time?.second?.format(
                                    DateTimeFormatter.ofPattern("HH:mm")
                                )
                            }",
                            color = inactiveContentColor,
                            fontFamily = sourcesanspro,
                            fontWeight = FontWeight.W400,
                            fontSize = 16.sp
                        )
                    }
                }
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = ImageVector.vectorResource(id = com.issart.coworking.android.R.drawable.ic_pin),
                        contentDescription = null,
                        tint = fontDescriptionColor
                    )
                    Spacer(modifier = Modifier.width(13.dp))
                    Column {
                        androidx.compose.material.Text(
                            text = room.name,
                            color = inactiveContentColor,
                            fontFamily = sourcesanspro,
                            fontWeight = FontWeight.W400,
                            fontSize = 16.sp
                        )
                        androidx.compose.material.Text(
                            text = room.address,
                            color = inactiveContentColor,
                            fontFamily = sourcesanspro,
                            fontWeight = FontWeight.W400,
                            fontSize = 16.sp
                        )
                    }
                }

            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}