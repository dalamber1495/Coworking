package com.issart.coworking.android.di


import com.issart.coworking.android.navigation.AppNavigation
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.viewModel.ResultScreenViewModel
import org.koin.dsl.module

val androidModule = module {
    single { AppNavigation() }

    single { ResultScreenViewModel() }

}
