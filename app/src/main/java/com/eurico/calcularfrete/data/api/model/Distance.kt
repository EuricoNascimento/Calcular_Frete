package com.eurico.calcularfrete.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Distance(
    @Json(name = "destination_addresses")
    val destinationAddresses: List<String>,
    @Json(name = "origin_addresses")
    val originAddresses: List<String>,
    @Json(name = "rows")
    val rows: List<Row>,
    @Json(name = "status")
    val status: String
)