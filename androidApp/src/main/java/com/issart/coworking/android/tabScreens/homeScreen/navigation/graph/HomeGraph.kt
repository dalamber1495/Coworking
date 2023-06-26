package com.issart.coworking.android.tabScreens.homeScreen.navigation.graph

import ResultScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.issart.coworking.android.tabScreens.homeScreen.detailScreen.view.DetailScreen
import com.issart.coworking.android.tabScreens.homeScreen.detailScreen.viewModel.DetailScreenViewModel
import com.issart.coworking.android.tabScreens.homeScreen.mapScreen.view.MapScreen
import com.issart.coworking.android.tabScreens.homeScreen.navigation.rootObject.HomeScreens
import com.issart.coworking.android.tabScreens.homeScreen.navigation.rootObject.homeGraphRoute
import com.issart.coworking.android.tabScreens.homeScreen.navigation.rootObject.roomId
import com.issart.coworking.android.tabScreens.homeScreen.payScreen.view.PayScreen
import com.issart.coworking.android.tabScreens.homeScreen.reservedScreen.view.ReservedScreen
import com.issart.coworking.android.tabScreens.homeScreen.reservedScreen.viewModel.ReservedScreenViewModel
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
        composable(route = HomeScreens.MapScreenRoute.route) {
            MapScreen(navController = navController)
        }

        composable(route = HomeScreens.ResultScreenRoute.route) {

            val viewModel = koinViewModel<ResultScreenViewModel>()
            ResultScreen(
                navController,
                viewModel.state,
                viewModel::onEvent,
                viewModel.filterOnBottomSheet
            )

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
        composable(HomeScreens.PayScreenRoute.route, arguments = listOf(
            navArgument(
                roomId
            ) {
                type = NavType.IntType
            }
        )) {
            PayScreen(navController, it.arguments?.getInt(roomId))
        }

        composable(route = HomeScreens.ReservedScreenRoute.route, arguments = listOf(
            navArgument(
                roomId
            ) {
                type = NavType.IntType
            }
        )) {
            val viewModel = koinViewModel<ReservedScreenViewModel>()
            ReservedScreen(
                navController, viewModel.state, viewModel::onEvent, it.arguments?.getInt(
                    roomId
                )
            )
        }

    }

}