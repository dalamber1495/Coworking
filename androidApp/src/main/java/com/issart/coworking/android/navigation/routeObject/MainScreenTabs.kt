package com.issart.coworking.android.navigation.routeObject

import com.issart.coworking.android.R


const val homeTab = "homeTab"
const val likeTab = "likeTab"
const val profileTab = "profileTab"
const val orderTab = "orderTab"

sealed class MainScreenTabRoute(val route: String, val title: String, val icon: Int) {
    object HomeTab: MainScreenTabRoute(homeTab, "Аренда", R.drawable.ic_home_tab)
    object LikeTab: MainScreenTabRoute(likeTab, "Избранное", R.drawable.ic_like_tab)
    object ProfileTab: MainScreenTabRoute(profileTab, "Профиль", R.drawable.ic_profile_tab)
    object OrderTab: MainScreenTabRoute(orderTab,"Корзина",R.drawable.ic_order_tab)
}
