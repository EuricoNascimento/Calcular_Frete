package com.eurico.calcularfrete.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Duration(
    @Json(name = "text")
    val text: String,
    @Json(name = "value")
    val value: Int
)