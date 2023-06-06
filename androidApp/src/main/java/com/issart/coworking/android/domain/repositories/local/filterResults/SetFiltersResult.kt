package com.issart.coworking.android.domain.repositories.local.filterResults

import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.FilterUiState
import kotlinx.coroutines.flow.SharedFlow

interface SetFiltersResult {
    val filters:SharedFlow<FilterUiState>
    suspend fun setFilters(filters:FilterUiState)
}