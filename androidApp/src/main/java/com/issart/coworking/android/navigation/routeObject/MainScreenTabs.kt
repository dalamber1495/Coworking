package com.issart.coworking.android.navigation.routeObject

import com.issart.coworking.android.R


const val homeTab = "homeTab"
const val profileTab = "profileTab"

sealed class MainScreenTabRoute(val route: String, val title: String, val icon: Int) {
    object HomeTab: MainScreenTabRoute(homeTab, "Аренда", R.drawable.ic_home_tab)
    object ProfileTab: MainScreenTabRoute(profileTab, "Профиль", R.drawable.ic_profile_tab)
}
