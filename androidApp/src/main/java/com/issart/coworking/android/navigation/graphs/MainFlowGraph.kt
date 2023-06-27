package com.issart.coworking.android.navigation.graphs


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.issart.coworking.android.navigation.routeObject.MainScreenTabRoute
import com.issart.coworking.android.navigation.routeObject.mainGraph
import com.issart.coworking.android.tabScreens.homeScreen.searchScreen.view.HomeScreen
import com.issart.coworking.android.tabScreens.profileScreen.view.ProfileScreen

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
        composable(route = MainScreenTabRoute.ProfileTab.route) {
            ProfileScreen()
        }
    }

}