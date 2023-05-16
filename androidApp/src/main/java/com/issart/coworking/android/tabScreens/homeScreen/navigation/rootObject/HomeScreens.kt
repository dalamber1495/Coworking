package com.issart.coworking.android.tabScreens.homeScreen.navigation.rootObject

import androidx.navigation.NavOptions

const val homeGraphRoute = "homeGraphRoute"
const val searchScreenRoute = "searchScreenRoute"
const val resultScreenRoute = "resultScreenRoute"
const val detailScreenRoute = "detailScreenRoute"
const val payScreenRoute = "payScreenRoute"


sealed class HomeScreens(
    var route: String,
    val options: NavOptions? = null,
    val inclusive: Boolean = false,
    val saveState: Boolean = false,
    val popTargetRoute: String = ""
) {
    object SearchScreenRoute : HomeScreens(
        route = searchScreenRoute,
        options = NavOptions.Builder().setPopUpTo(0, inclusive = false).build()
    )
    object ResultScreenRoute: HomeScreens(route = resultScreenRoute)
    object DetailScreenRoute:HomeScreens(route = detailScreenRoute)
    object PayScreenRoute: HomeScreens( route = payScreenRoute)
}
