package com.eurico.calcularfrete.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Element(
    @Json(name = "distance")
    val distance: DistanceX,
    @Json(name = "duration")
    val duration: Duration,
    @Json(name = "status")
    val status: String
)