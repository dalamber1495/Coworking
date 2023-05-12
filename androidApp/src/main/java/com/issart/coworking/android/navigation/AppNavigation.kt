package com.issart.coworking.android.navigation

import com.issart.coworking.android.navigation.routeObject.AppScreens
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class AppNavigation {
    private val _navRoute =
        MutableSharedFlow<AppScreens>(extraBufferCapacity = 1)
    val navRoute = _navRoute.asSharedFlow()

    fun navigateTo(destination: AppScreens) {
        _navRoute.tryEmit(destination)
    }
}