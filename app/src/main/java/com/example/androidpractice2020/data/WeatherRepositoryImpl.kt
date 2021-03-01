package com.example.androidpractice2020.data

import com.example.androidpractice2020.data.api.WeatherApi
import com.example.androidpractice2020.domain.WeatherRepository
import java.lang.Exception
import java.net.UnknownHostException

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi,
    private val weatherDao: WeatherDao
) : WeatherRepository {


    override suspend fun getWeatherByName(cityName: String) =
        try {
            val weatherResponse = weatherApi.getWeather(cityName)
            City(
                weatherResponse.id,
                weatherResponse.name,
                weatherResponse.main.temp,
                weatherResponse.weather[0].description,
                weatherResponse.main.humidity,
                weatherResponse.wind.deg
            )

        } catch (e: Exception) {
            when (e) {
                is UnknownHostException -> weatherDao.getCityByName(cityName)
                else -> City(
                    -1,
                    "",
                    -1.0,
                    "",
                    -1,
                    1
                )
            }
        }

    override suspend fun getCityById(cityId: Int): City =
        try {
            val weatherResponse = weatherApi.getCityById(cityId)
            City(
                weatherResponse.id,
                weatherResponse.name,
                weatherResponse.main.temp,
                weatherResponse.weather[0].description,
                weatherResponse.main.humidity,
                weatherResponse.wind.deg
            )
        } catch (e: UnknownHostException) {
            weatherDao.getCityById(cityId)
        }

    override suspend fun getCitiesWeather(
        latitudeUser: Double,
        longitude: Double,
        countCities: Int
    ): ArrayList<City> {
        var result: ArrayList<City> = arrayListOf()
        try {
            var weatherResponse =
                weatherApi.getCitiesWeather(latitudeUser, longitude, countCities).list
            var i = 0
            while (i < weatherResponse.size) {
                result.add(City.mapResponsetoEntity(weatherResponse[i]))
                i++
            }
            weatherDao.deleteAllCity()
            weatherDao.insert(result)
        } catch (e: UnknownHostException) {
            result = weatherDao.getAllCities() as ArrayList<City>
        }
        return result
    }

//    @SuppressLint("MissingPermission")
//    override suspend fun getGeoPositionUser(
//        applicationContext: Context,
//        boolean: Boolean
//    ): DoubleArray {
//        var fusedLocationProviderClient =
//            LocationServices.getFusedLocationProviderClient(applicationContext)
//        var longitude = 37.6156
//        var latitude = 55.7522
//        fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
//            var location: Location? = task.result
//            if (location != null) {
//                longitude = location.longitude
//                latitude = location.latitude
//                Log.e("qweqw", "$latitude")
//                Log.e("qweqwe", "$longitude")
//            }
//        }
//        return doubleArrayOf(latitude, longitude)
//    }
}