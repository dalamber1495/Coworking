package com.issart.coworking.android.tabScreens.homeScreen.navigation.graph

import ResultScreen
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.issart.coworking.android.tabScreens.homeScreen.detailScreen.DetailScreen
import com.issart.coworking.android.tabScreens.homeScreen.detailScreen.viewModel.DetailScreenViewModel
import com.issart.coworking.android.tabScreens.homeScreen.mapScreen.view.MapScreen
import com.issart.coworking.android.tabScreens.homeScreen.navigation.rootObject.HomeScreens
import com.issart.coworking.android.tabScreens.homeScreen.navigation.rootObject.homeGraphRoute
import com.issart.coworking.android.tabScreens.homeScreen.navigation.rootObject.roomId
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.viewModel.ResultScreenViewModel
import com.issart.coworking.android.tabScreens.homeScreen.searchScreen.view.SearchScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = HomeScreens.SearchScreenRoute.route,
        route = homeGraphRoute
    ) {
        composable(route = HomeScreens.SearchScreenRoute.route) {

            SearchScreen(navController)

        }
        composable(route = HomeScreens.MapScreenRoute.route){
            MapScreen(navController = navController)
        }

        composable(route = HomeScreens.ResultScreenRoute.route) {

            val viewModel = koinViewModel<ResultScreenViewModel>()
            ResultScreen(navController, viewModel.state, viewModel::onEvent)

        }
        composable(
            route = HomeScreens.DetailScreenRoute.route, arguments = listOf(
                navArgument(
                    roomId
                ) {
                    type = NavType.IntType
                }
            )
        ) {

            val viewModel = koinViewModel<DetailScreenViewModel>()
            DetailScreen(
                navController = navController,
                stateFlow = viewModel.state,
                onEvent = viewModel::onEvent,
                it.arguments?.getInt(
                    roomId
                )
            )

        }

    }

}