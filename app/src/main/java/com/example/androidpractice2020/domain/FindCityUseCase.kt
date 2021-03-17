package com.example.androidpractice2020.domain

import com.example.androidpractice2020.data.database.entity.City
import com.example.androidpractice2020.data.database.repository.WeatherRepositoryImpl
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class FindCityUseCase(
    private val weatherRepositoryImpl: WeatherRepositoryImpl,
    private val context: CoroutineContext
) {

    suspend fun findWeatherCityByName(cityName: String): City =
        withContext(context) {
            weatherRepositoryImpl.getWeatherByName(cityName)
        }

    suspend fun findWeatherCityById(cityId: Int): City =
        withContext(context) {
            weatherRepositoryImpl.getCityById(cityId)
        }

    suspend fun findWeatherCitiesByCoordinates(
        latitudeUser: Double,
        longitudeUser: Double,
        countCities: Int
    ): ArrayList<City> =
        withContext(context) {
            weatherRepositoryImpl.getCitiesWeather(latitudeUser, longitudeUser, countCities)
        }

//    suspend fun getCoordinatesByUser(
//        applicationContext: Context, boolean: Boolean
//    ): DoubleArray =
//        withContext(context) {
//            weatherRepositoryImpl.getGeoPositionUser(applicationContext, boolean)
//        }
}