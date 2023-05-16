package com.issart.coworking.android.ui

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

val textPlaceHolderTextStyle = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.W400,
    fontStyle = FontStyle.Normal,
    color = inactiveContentColor,
    fontFamily = sourcesanspro,
)
val buttonTextStyle = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.W600,
    fontStyle = FontStyle.Normal,
    color = backgroundColor,
    fontFamily = sourcesanspro,
)

val descriptionTextStyle = TextStyle(
    fontSize = 40.sp,
    color = fontDescriptionColor,
    fontFamily = playfaird,
    fontWeight = FontWeight.W400,
    textAlign = TextAlign.Start
)
val filterTextStyle = TextStyle(
    fontSize = 13.sp,
    color = inactiveContentColor,
    fontFamily = sourcesanspro,
    fontWeight = FontWeight.W400,
    textAlign = TextAlign.Start
)

val nameItemTextStyle = TextStyle(
    fontSize = 18.sp,
    color = fontDescriptionColor,
    fontFamily = playfaird,
    fontWeight = FontWeight.W400,
    textAlign = TextAlign.Start
)
val titleItemTextStyle = TextStyle(
    fontSize = 14.sp,
    color = inactiveContentColor,
    fontFamily = playfaird,
    fontWeight = FontWeight.W400,
    textAlign = TextAlign.Start
)

val coastItemTextStyle = TextStyle(
    fontSize = 16.sp,
    color = activeContentColor,
    fontFamily = sourcesanspro,
    fontWeight = FontWeight.W600,
    textAlign = TextAlign.Start
)