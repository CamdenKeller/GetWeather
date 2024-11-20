package com.example.p5

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherAPI {
    @GET("v1/weather")
    @Headers("X-Api-Key: YQhN1nGNi9HGc7K79k6Sb07c2fdgI6nGJif8PyKN")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Response<Weather>
}
