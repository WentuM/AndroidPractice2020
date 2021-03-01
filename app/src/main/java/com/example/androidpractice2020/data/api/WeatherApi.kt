package com.example.androidpractice2020.data.api

import com.example.androidpractice2020.data.api.response.CitiesWeatherResponse
import com.example.androidpractice2020.data.api.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather?lang=ru")
    suspend fun getWeather(
        @Query("q") cityName: String
    ): WeatherResponse

    @GET("find?lang=ru")
    suspend fun getCitiesWeather(
        @Query("lat") latitudeUser: Double,
        @Query("lon") longitude: Double,
        @Query("cnt") countCities: Int
    ): CitiesWeatherResponse

    @GET("weather?lang=ru")
    suspend fun getCityById(
        @Query("id") cityId: Int
    ): WeatherResponse
}