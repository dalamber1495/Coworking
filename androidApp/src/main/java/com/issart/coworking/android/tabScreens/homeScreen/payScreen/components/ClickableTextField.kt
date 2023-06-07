package com.issart.coworking.android.tabScreens.homeScreen.payScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.issart.coworking.android.ui.backgroundFieldColor
import com.issart.coworking.android.ui.inactiveContentColor
import com.issart.coworking.android.ui.sourcesanspro

@Composable
fun ClickableTextField(
    modifier: Modifier = Modifier,
    iconResourse: Int? = null,
    text: String = "",
    contentColor: Color = inactiveContentColor
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(color = backgroundFieldColor, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.matchParentSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            iconResourse?.let {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = ImageVector.vectorResource(id = iconResourse),
                    contentDescription = null,
                    tint = contentColor
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = text,
                color = contentColor,
                fontFamily = sourcesanspro,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp
            )
        }
    }

}