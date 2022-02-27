package com.eurico.calcularfrete.entity

import java.math.BigDecimal

data class CalculateInfo(
    val start: String,
    val end: String,
    val consumable: BigDecimal,
    val price: BigDecimal
)
