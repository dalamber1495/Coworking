package com.issart.coworking.android.di


import com.issart.coworking.android.navigation.AppNavigation
import org.koin.dsl.module

val androidModule = module {
    single { AppNavigation() }


}
