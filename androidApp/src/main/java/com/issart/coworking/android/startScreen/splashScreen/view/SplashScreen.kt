package com.issart.coworking.android.startScreen.splashScreen.view

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.issart.coworking.android.R
import com.issart.coworking.android.navigation.routeObject.routeGraph
import com.issart.coworking.android.ui.activeContentColor
import com.issart.coworking.android.ui.backgroundColor
import com.issart.coworking.android.ui.playfaird
import kotlinx.coroutines.delay


@Composable
fun SplashScreenView(
    navigator: NavHostController,
    startingGraph: String
) {

    val visibleState = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = null) {
        visibleState.value = true
        delay(600)
        navigator.navigate(startingGraph, NavOptions.Builder().setPopUpTo(routeGraph, true).build())
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        var height = remember{ mutableStateOf(0.dp) }
        BoxWithConstraints(modifier = Modifier.wrapContentSize()) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.splash_img),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            height.value = maxHeight
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor.copy(alpha = 0.8f))
        )
        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.TopStart),
            visible = visibleState.value,
        enter = slideInVertically(animationSpec = tween(400), initialOffsetY = {-it/2})) {
            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 120.dp, start = 50.dp),
                text = "Meeting\nplace",
                fontFamily = playfaird,
                color = activeContentColor,
                fontWeight = FontWeight.W400,
                fontSize = 64.sp
            )
        }

        AnimatedVisibility(modifier = Modifier
            .align(Alignment.BottomStart)
            .fillMaxWidth(0.98f)
            .height((height.value.value * 0.425).dp),
                visible = visibleState.value,
        enter = fadeIn(animationSpec = tween(600))
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth(0.98f)
                    .height((height.value.value * 0.425).dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.chairs),
                contentScale = ContentScale.FillBounds,
                contentDescription = null
            )
        }


    }
}
