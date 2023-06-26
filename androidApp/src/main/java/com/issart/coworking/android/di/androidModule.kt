package com.issart.coworking.android.di


import android.location.Geocoder
import com.issart.coworking.android.authScreen.viewModels.AuthScreenViewModel
import com.issart.coworking.android.data.repositories.local.RoomRepositoryImpl
import com.issart.coworking.android.domain.repositories.local.filterResults.SetFiltersResult
import com.issart.coworking.android.domain.repositories.local.filterResults.SetFiltersResultImpl
import com.issart.coworking.android.domain.repositories.local.geoMapResult.SetGeoMapResult
import com.issart.coworking.android.domain.repositories.local.geoMapResult.SetGeoMapResultImpl
import com.issart.coworking.android.domain.repositories.local.useCases.GetRoomListUseCase
import com.issart.coworking.android.domain.repositories.local.getRoomList.RoomRepository
import com.issart.coworking.android.domain.repositories.local.useCases.GetRoomByIdUseCase
import com.issart.coworking.android.domain.repositories.local.useCases.UpdateRoomUseCase
import com.issart.coworking.android.navigation.AppNavigation
import com.issart.coworking.android.tabScreens.homeScreen.detailScreen.viewModel.DetailScreenViewModel
import com.issart.coworking.android.tabScreens.homeScreen.mapScreen.viewModel.MapViewModel
import com.issart.coworking.android.tabScreens.homeScreen.reservedScreen.viewModel.ReservedScreenViewModel
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
    single { GetRoomListUseCase(get()) }
    single <RoomRepository>{ RoomRepositoryImpl() }
    single { GetRoomByIdUseCase(get()) }
    single { GetRoomListUseCase(get()) }
    single { UpdateRoomUseCase(get()) }

    viewModel { MapViewModel(get())}
    viewModel { ResultScreenViewModel(get(), get(), get(), get()) }
    viewModel { DetailScreenViewModel(get(), get(),get(), get()) }
    viewModel { AuthScreenViewModel() }
    viewModel { SearchScreenViewModel(get(), get()) }
    viewModel { ReservedScreenViewModel(get(), get())}

}
