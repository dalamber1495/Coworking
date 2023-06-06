package com.issart.coworking.android.tabScreens.homeScreen.mapScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issart.coworking.android.domain.repositories.local.geoMapResult.SetGeoMapResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MapViewModel(
    private val setGeoMapResult: SetGeoMapResult
) : ViewModel() {

    private var latitude = 55.754405
    private var longitude = 37.619992

    fun setGeo() {
        viewModelScope.launch(Dispatchers.IO) {
            setGeoMapResult.setGeoAddress(longitude, latitude)
        }
    }

    fun setLatLng(latitude:Double, longitude:Double){
        this.latitude = latitude
        this.longitude = longitude
    }


}