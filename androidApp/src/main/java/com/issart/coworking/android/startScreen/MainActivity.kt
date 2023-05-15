package com.issart.coworking.android.startScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.issart.coworking.android.MyApplicationTheme
import com.issart.coworking.android.navigation.AppNavigation
import com.issart.coworking.android.navigation.routeObject.popRouteName
import com.issart.coworking.android.navigation.graphs.authGraph
import com.issart.coworking.android.navigation.routeObject.AppScreens
import com.issart.coworking.android.navigation.routeObject.nonLoggedUserGraph
import com.issart.coworking.android.navigation.routeObject.routeGraph
import com.issart.coworking.android.tabScreens.MainScreen
import com.issart.coworking.android.ui.backgroundColor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.get

class MainActivity : ComponentActivity() {

    private val navigator: AppNavigation = get()


    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val startingGraph = nonLoggedUserGraph
//            AppScreens.MainAppScreen.route

        setContent {
            MyApplicationTheme {
                val navigationController = rememberNavController()
                LaunchedEffect(Unit) {
                    navigator.navRoute.onEach {
                        when (it.route) {
                            popRouteName -> {
                                when (it.popTargetRoute.isEmpty()) {
                                    true -> navigationController.popBackStack()
                                    false -> navigationController.popBackStack(
                                        it.popTargetRoute,
                                        it.inclusive,
                                        it.saveState
                                    )
                                }
                            }
                            else -> navigationController.navigate(it.route, it.options)
                        }
                    }.launchIn(this)
                }

                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor)) {
                    NavHost(
                        navController = navigationController,
                        route = routeGraph,
                        startDestination = startingGraph
                    ) {


                        authGraph(navigationController)
                        composable(route = AppScreens.MainAppScreen.route) {
                            MainScreen()
                        }


                    }
                }
            }
        }
    }
}


