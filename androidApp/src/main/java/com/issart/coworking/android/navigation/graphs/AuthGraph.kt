package com.issart.coworking.android.navigation.graphs

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.issart.coworking.android.authScreen.AuthScreen
import com.issart.coworking.android.navigation.routeObject.AppScreens
import com.issart.coworking.android.navigation.routeObject.nonLoggedUserGraph

@ExperimentalComposeUiApi
fun NavGraphBuilder.authGraph(navController:NavHostController) {
    navigation(startDestination = AppScreens.AuthSignUp.route, route = nonLoggedUserGraph) {
        composable(route = AppScreens.AuthSignUp.route) {
            AuthScreen(navController)
    }
    }
}