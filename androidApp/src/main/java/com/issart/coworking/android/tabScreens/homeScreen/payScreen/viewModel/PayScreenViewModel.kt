package com.issart.coworking.android.tabScreens.homeScreen.payScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issart.coworking.android.domain.repositories.local.filterResults.SetFiltersResult
import com.issart.coworking.android.domain.repositories.local.useCases.AddRoomInReservedUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

class PayScreenViewModel(
    private val addRoomInReservedUseCase: AddRoomInReservedUseCase,
    private val setFiltersResult: SetFiltersResult
) : ViewModel() {

    private var date = LocalDate.now()
    private var time = LocalTime.now() to LocalTime.now()

    init {
        setFiltersResult.filters.onEach {
            date = it.dateFilter
            it.timeFilter?.let { timeFilter ->
                time = timeFilter
            }
        }.launchIn(viewModelScope)
    }

    fun addRoomInReserved(id: Int){
        viewModelScope.launch {
            addRoomInReservedUseCase.invoke(id, date, time)
        }
    }

}