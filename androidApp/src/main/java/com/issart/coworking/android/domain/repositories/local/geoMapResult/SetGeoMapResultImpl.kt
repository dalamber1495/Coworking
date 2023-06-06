package com.issart.coworking.android.domain.repositories.local.geoMapResult

import android.location.Geocoder
import com.issart.coworking.android.domain.model.geoModel.GeoAddress
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class SetGeoMapResultImpl(
    private val geocoder: Geocoder
) : SetGeoMapResult {

    private val _geoAddress = MutableSharedFlow<GeoAddress>(1)
    override val geoAddress: SharedFlow<GeoAddress> = _geoAddress.asSharedFlow()
    override suspend fun setGeoAddress(longitude: Double, latitude: Double) {
        _geoAddress.emit(GeoAddress(latitude,longitude,null))
        val address = geocoder.getFromLocation(latitude,longitude,1)
        address?.first()?.let {
            _geoAddress.emit(GeoAddress(latitude,longitude,it.subAdminArea))
        }
    }


}