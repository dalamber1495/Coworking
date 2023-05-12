package com.issart.coworking.android.navigation.routeObject

import androidx.navigation.NavOptions


const val mainScreenRoute = "mainScreen"

const val popRouteName = "popRoute"

const val loggedUserGraph = "loggedGraph"

const val nonLoggedUserGraph = "nonLoggedUserGraph"

const val authSignUpRoute = "authScreen"

const val routeGraph = "routeGraph"

const val mainGraph = "mainGraph"



sealed class AppScreens(
    var route: String,
    val options: NavOptions? = null,
    val inclusive: Boolean = false,
    val saveState: Boolean = false,
    val popTargetRoute: String = ""
) {



    
    object AuthSignUp: AppScreens(
        route = authSignUpRoute,
        options = NavOptions.Builder().setPopUpTo(0, false).build()
    )

    object Route

    object MainAppScreen: AppScreens(
        route = mainScreenRoute,
        options = NavOptions.Builder().setPopUpTo(0, false).build()
    )


    object PopBackToMain: AppScreens(
        route = popRouteName,
        popTargetRoute = mainScreenRoute
    )

    object PopBackStack: AppScreens(popRouteName)

}
