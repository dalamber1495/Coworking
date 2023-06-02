package com.issart.coworking.android.navigation

import com.issart.coworking.android.navigation.routeObject.AppScreens
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow

class AppNavigation {
    private val _navRoute =
        Channel<AppScreens>(capacity = 1)
    val navRoute = _navRoute.receiveAsFlow()

    fun navigateTo(destination: AppScreens) {
        _navRoute.trySend(destination)
    }
}