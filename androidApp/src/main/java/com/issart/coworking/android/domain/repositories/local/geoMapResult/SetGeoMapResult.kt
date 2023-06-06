package com.issart.coworking.android.domain.repositories.local.geoMapResult

import com.issart.coworking.android.domain.model.geoModel.GeoAddress
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

interface SetGeoMapResult {
    val geoAddress:SharedFlow<GeoAddress>
    suspend fun setGeoAddress(longitude:Double, latitude:Double)

}