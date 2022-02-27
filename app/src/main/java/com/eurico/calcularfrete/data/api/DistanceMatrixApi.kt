package com.eurico.calcularfrete.data.api

import com.eurico.calcularfrete.data.api.model.Distance
import retrofit2.http.GET
import retrofit2.http.Query

interface DistanceMatrixApi {

    @GET(ApiConstants.END_POINT)
    suspend fun getDistance(
        @Query("origins") origin: String,
        @Query("destinations") destin: String,
        @Query("key") key: String
    ): Distance
}