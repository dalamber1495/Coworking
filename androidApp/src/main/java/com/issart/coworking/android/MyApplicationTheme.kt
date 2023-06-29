package com.issart.coworking.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.issart.coworking.android.ui.activeContentColor
import com.issart.coworking.android.ui.backgroundColor


private val DarkColorScheme = darkColorScheme(
    primary = activeContentColor,
    secondary = backgroundColor,
    tertiary = activeContentColor,
    background = backgroundColor,
    onBackground = backgroundColor,
    primaryContainer = activeContentColor,
    secondaryContainer = activeContentColor.copy(alpha = 0.3f)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        lightColors(
            primary = activeContentColor,
            primaryVariant = activeContentColor,
            secondary = Color(0xFF03DAC5),
            background = backgroundColor
        )
    } else {
        lightColors(
            primary = activeContentColor,
            primaryVariant = activeContentColor,
            secondary = Color(0xFF03DAC5),
            background = backgroundColor
        )
    }
    val typography = androidx.compose.material3.Typography(
        bodyLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    androidx.compose.material3.MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = typography,
        content = {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(Color.Transparent,false)
            systemUiController.setNavigationBarColor(Color.Transparent,darkIcons = false, navigationBarContrastEnforced = false)
            content.invoke()
        }
        )
}
