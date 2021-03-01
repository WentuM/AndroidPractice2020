package com.example.androidpractice2020.data

import android.app.Application
import com.example.androidpractice2020.data.api.ApiFactory

class WeatherApplication : Application() {

    val database by lazy {
        WeatherRoomDatabase.getDataBase(this)
    }

    val repository by lazy {
        WeatherRepositoryImpl(ApiFactory.weatherApi ,database.weatherDao())
    }
}