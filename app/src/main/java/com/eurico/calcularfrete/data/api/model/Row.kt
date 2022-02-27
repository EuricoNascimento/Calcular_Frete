package com.eurico.calcularfrete.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Row(
    @Json(name = "elements")
    val elements: List<Element>
)