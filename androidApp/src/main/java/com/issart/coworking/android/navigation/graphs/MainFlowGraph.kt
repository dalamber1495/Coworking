package com.issart.coworking.android.navigation.graphs


import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.issart.coworking.android.navigation.routeObject.AppScreens
import com.issart.coworking.android.navigation.routeObject.MainScreenTabRoute
import com.issart.coworking.android.navigation.routeObject.loggedUserGraph
import com.issart.coworking.android.navigation.routeObject.mainGraph
import com.issart.coworking.android.tabScreens.homeScreen.searchScreen.view.HomeScreen

@Composable
fun MainFlowGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = mainGraph,
        startDestination = MainScreenTabRoute.HomeTab.route
    ){
        composable(route = MainScreenTabRoute.HomeTab.route) {

            HomeScreen()

        }
        composable(route = MainScreenTabRoute.LikeTab.route) {

        }
        composable(route = MainScreenTabRoute.ProfileTab.route) {

        }
        composable(route = MainScreenTabRoute.OrderTab.route) {

        }
    }

}