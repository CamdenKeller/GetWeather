package com.example.p5

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Weather(
    val temp: Int,
    val humidity: Int,
    val sunset: Long,
    val min_temp: Int,
    val feels_like: Int,
    val sunrise: Long,
    val max_temp: Int
)
