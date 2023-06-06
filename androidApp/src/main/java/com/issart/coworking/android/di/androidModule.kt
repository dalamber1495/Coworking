package com.issart.coworking.android.di


import android.location.Geocoder
import com.issart.coworking.android.authScreen.viewModels.AuthScreenViewModel
import com.issart.coworking.android.domain.repositories.local.filterResults.SetFiltersResult
import com.issart.coworking.android.domain.repositories.local.filterResults.SetFiltersResultImpl
import com.issart.coworking.android.domain.repositories.local.geoMapResult.SetGeoMapResult
import com.issart.coworking.android.domain.repositories.local.geoMapResult.SetGeoMapResultImpl
import com.issart.coworking.android.navigation.AppNavigation
import com.issart.coworking.android.tabScreens.homeScreen.detailScreen.viewModel.DetailScreenViewModel
import com.issart.coworking.android.tabScreens.homeScreen.mapScreen.viewModel.MapViewModel
import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.viewModel.ResultScreenViewModel
import com.issart.coworking.android.tabScreens.homeScreen.searchScreen.viewModel.SearchScreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.*

val androidModule = module {
    single { AppNavigation() }


    single {
        Geocoder(androidContext(), Locale.getDefault())
    }
    single <SetGeoMapResult>{ SetGeoMapResultImpl(get()) }
    single <SetFiltersResult>{SetFiltersResultImpl()  }

    viewModel { MapViewModel(get())}
    viewModel { ResultScreenViewModel(get()) }
    viewModel { DetailScreenViewModel() }
    viewModel { AuthScreenViewModel() }
    viewModel { SearchScreenViewModel(get(), get()) }

}
