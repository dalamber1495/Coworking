package com.issart.coworking.android.tabScreens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.issart.coworking.android.navigation.graphs.MainFlowGraph
import com.issart.coworking.android.navigation.routeObject.MainScreenTabRoute
import com.issart.coworking.android.ui.activeContentColor
import com.issart.coworking.android.ui.backgroundColor
import com.issart.coworking.android.ui.backgroundFieldColor
import com.issart.coworking.android.ui.inactiveContentColor

@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        backgroundColor = backgroundColor,
        bottomBar = { BottomBar(navController = navController) }) {
        it
        MainFlowGraph(navController = navController)
    }

}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        MainScreenTabRoute.HomeTab,
        MainScreenTabRoute.ProfileTab,
        )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    val animateHome = animateDpAsState(
        targetValue = if (currentDestination?.hierarchy?.any {
                it.route == MainScreenTabRoute.HomeTab.route
            } == true) 40.dp else 0.dp,
        animationSpec = tween(
            durationMillis = 300,
            easing = LinearEasing
        )
    )

    val animateProfile = animateDpAsState(
        targetValue = if (currentDestination?.hierarchy?.any {
                it.route == MainScreenTabRoute.ProfileTab.route
            } == true) 40.dp else 0.dp,
        animationSpec = tween(
            durationMillis = 300,
            easing = LinearEasing
        )
    )


    if (bottomBarDestination) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            val sizeItem = maxWidth / 2

            Row(verticalAlignment = Alignment.Bottom) {
                SelectedOval(width = sizeItem, animateHeight = animateHome.value)
                SelectedOval(width = sizeItem, animateHeight = animateProfile.value)
            }
                BottomNavigation(
                    modifier = Modifier, backgroundColor = Color.Transparent, elevation = 0.dp
                ) {
                    screens.forEach { screen ->
                        AddItem(
                            screen = screen,
                            currentDestination = currentDestination,
                            navController = navController
                        )
                    }

                }


        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: MainScreenTabRoute,
    currentDestination: NavDestination?,
    navController: NavHostController
) {


        BottomNavigationItem(modifier = Modifier
            .background(color = backgroundFieldColor),
            label = {
                Text(text = screen.title, color = activeContentColor)
            },
            alwaysShowLabel = false,
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = screen.icon),
                    contentDescription = "Navigation Icon",
                    tint = if (currentDestination?.hierarchy?.any {
                            it.route == screen.route
                        } == true) activeContentColor else inactiveContentColor
                )
            },
            selected = currentDestination?.hierarchy?.any {
                it.route == screen.route
            } == true,
            unselectedContentColor = inactiveContentColor,
            selectedContentColor = backgroundFieldColor,
            onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id){
                        saveState = true
                    }
                    restoreState = true
                    launchSingleTop = true
                }
            })

}

@Composable
fun SelectedOval(width: Dp, animateHeight: Dp) {
    Box(
        modifier = Modifier
            .padding(bottom = 38.dp)
            .height(71.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Canvas(
            modifier = Modifier
                .size(width = width, height = 30.dp)
        ) {
            drawOval(
                color = backgroundFieldColor,
                size = Size(width = width.toPx(), height = animateHeight.toPx()),
                topLeft = Offset(x = 0.dp.toPx(), y = (15.dp - animateHeight / 2).toPx())

            )
        }
    }
}
