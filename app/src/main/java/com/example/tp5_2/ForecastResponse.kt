package com.example.tp5_2

data class ForecastResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<list>,
    val message: Double
)