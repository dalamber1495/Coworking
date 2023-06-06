package com.issart.coworking.android.domain.repositories.local.filterResults

import com.issart.coworking.android.tabScreens.homeScreen.resultScreen.data.FilterUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class SetFiltersResultImpl:SetFiltersResult {
    private val _filters = MutableSharedFlow<FilterUiState>(1,1)
    override val filters: SharedFlow<FilterUiState> = _filters.asSharedFlow()

    override suspend fun setFilters(filters: FilterUiState) {
        _filters.emit(filters)
    }
}