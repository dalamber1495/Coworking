package com.issart.coworking.android.di


import com.issart.coworking.android.authScreen.viewModels.AuthScreenViewModel
import com.issart.coworking.android.navigation.AppNavigation
import com.issart.coworking.android.tabScreens.homeScreen.detailScreen.viewModel.DetailScreenViewModel
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.viewModel.ResultScreenViewModel
import com.issart.coworking.android.tabScreens.homeScreen.searchScreen.viewModel.SearchScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    single { AppNavigation() }

    viewModel { ResultScreenViewModel() }
    viewModel { DetailScreenViewModel() }
    viewModel { AuthScreenViewModel() }
    viewModel { SearchScreenViewModel()}

}
