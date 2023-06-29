package com.issart.coworking.android.startScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.issart.coworking.android.MyApplicationTheme
import com.issart.coworking.android.navigation.AppNavigation
import com.issart.coworking.android.navigation.graphs.authGraph
import com.issart.coworking.android.navigation.routeObject.*
import com.issart.coworking.android.startScreen.splashScreen.view.SplashScreenView
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
        WindowCompat.setDecorFitsSystemWindows(window, false)
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
                    .background(backgroundColor)
                    .statusBarsPadding()) {
                    NavHost(
                        navController = navigationController,
                        route = routeGraph,
                        startDestination = splashScreenRoute
                    ) {

                        composable(route = splashScreenRoute){
                            SplashScreenView(navigator = navigationController, startingGraph = startingGraph)
                        }
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


