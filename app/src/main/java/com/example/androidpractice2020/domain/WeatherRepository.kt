package com.example.androidpractice2020.domain

import com.example.androidpractice2020.data.City

interface WeatherRepository {

    suspend fun getWeatherByName(
        cityName: String
    ): City

    suspend fun getCityById(
        cityId: Int
    ): City

    suspend fun getCitiesWeather(
        latitudeUser: Double,
        longitude: Double,
        countCities: Int
    ): ArrayList<City>

//    suspend fun getGeoPositionUser(
//        applicationContext: Context, boolean: Boolean
//    ): DoubleArray
}