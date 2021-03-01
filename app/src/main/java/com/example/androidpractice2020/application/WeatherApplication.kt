package com.example.androidpractice2020.application

import android.app.Application
import com.example.androidpractice2020.data.api.ApiFactory
import com.example.androidpractice2020.data.database.WeatherRoomDatabase
import com.example.androidpractice2020.data.database.repository.WeatherRepositoryImpl

class WeatherApplication : Application() {

    val database by lazy {
        WeatherRoomDatabase.getDataBase(this)
    }

    val repository by lazy {
        WeatherRepositoryImpl(
            ApiFactory.weatherApi,
            database.weatherDao()
        )
    }
}