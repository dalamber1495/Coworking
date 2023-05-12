package com.issart.coworking.android.startScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.issart.coworking.android.MyApplicationTheme
import com.issart.coworking.android.navigation.AppNavigation
import com.issart.coworking.android.navigation.routeObject.popRouteName
import com.issart.coworking.android.navigation.graphs.authGraph
import com.issart.coworking.android.navigation.routeObject.AppScreens
import com.issart.coworking.android.navigation.routeObject.routeGraph
import com.issart.coworking.android.tabScreens.MainScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.get

class MainActivity : ComponentActivity() {

    private val navigator: AppNavigation = get()


    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val startingGraph = AppScreens.MainAppScreen.route

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


                NavHost(
                    navController = navigationController,
                    route = routeGraph,
                    startDestination = startingGraph
                ) {

                    authGraph()
                    composable(route = AppScreens.MainAppScreen.route){
                        MainScreen()
                    }


                }


            }
        }
    }
}


