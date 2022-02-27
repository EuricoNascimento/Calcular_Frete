package com.eurico.calcularfrete.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import com.eurico.calcularfrete.data.api.model.Distance

interface DistanceMatrixApi {

    @GET(ApiConstants.END_POINT)
    suspend fun getDistance(
        @Query("destinations") destin: String,
        @Query("origins") origin: String,
        @Query("key") key: String
    ): Distance
}