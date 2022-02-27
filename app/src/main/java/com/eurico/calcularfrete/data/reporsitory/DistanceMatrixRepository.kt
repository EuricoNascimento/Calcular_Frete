package com.eurico.calcularfrete.data.reporsitory

import com.eurico.calcularfrete.R
import com.eurico.calcularfrete.data.api.DistanceMatrixApi
import com.eurico.calcularfrete.data.api.model.Distance
import javax.inject.Inject

class DistanceMatrixRepository @Inject constructor(
    private val distanceMatrixApi: DistanceMatrixApi
) {
    suspend fun getDistance(origin: String, destin: String, key: String = "${R.string.API_KEY}"): Distance{
        return distanceMatrixApi.getDistance(origin = origin, destin = destin, key = key)
    }

}