package com.issart.coworking.android.tabScreens.homeScreen.navigation.graph

import ResultScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.issart.coworking.android.tabScreens.homeScreen.navigation.rootObject.HomeScreens
import com.issart.coworking.android.tabScreens.homeScreen.navigation.rootObject.homeGraphRoute
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
        composable(route = HomeScreens.SearchScreenRoute.route){

            SearchScreen(navController)

        }

        composable(route = HomeScreens.ResultScreenRoute.route){

            val viewModel = koinViewModel<ResultScreenViewModel>()
            ResultScreen(navController, viewModel.state, viewModel::onEvent)

        }

    }

}