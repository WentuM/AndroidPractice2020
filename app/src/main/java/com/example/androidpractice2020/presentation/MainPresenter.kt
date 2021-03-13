package com.example.androidpractice2020.presentation

import android.util.Log
import com.example.androidpractice2020.data.LocationRepositoryImpl
import com.example.androidpractice2020.data.database.entity.City
import com.example.androidpractice2020.domain.FindCityUseCase
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope
import java.lang.NullPointerException

class MainPresenter(
    private val findCityUseCase: FindCityUseCase,
    private val locationRepositoryImpl: LocationRepositoryImpl
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Log.d("qwe10", "qweqwewq")
        getListCities()
    }


    fun getListCities() {
        var longitude = 37.6156
        var latitude = 55.7522
        var arrayCity: ArrayList<City> = ArrayList()
        presenterScope.launch {
            try {
                viewState.showLoading()
                locationRepositoryImpl.getUserLocation().also {
                    viewState.getListCities(
                        arrayCity = loadCityAroundUser(
                            it.latitude,
                            it.longitude,
                            15
                        )
                    )
                    Log.d("qwe2", "${arrayCity.size}")
                }
            } catch (e: NullPointerException) {
                viewState.showLoading()
                viewState.getListCities(arrayCity = loadCityAroundUser(latitude, longitude, 15))
//            viewState.consumeError(throwable)
                Log.d("qwe3", "${arrayCity.size}")
            } finally {
                viewState.hideLoading()
            }
        }
    }

    fun loadCityAroundUser(
        latitude: Double,
        longitude: Double,
        //на будущее, если число городов будет меняться по решению пользователя
        countCities: Int
    ): ArrayList<City> {
        var arrayCity: ArrayList<City> = ArrayList()
        presenterScope.launch {
            findCityUseCase.findWeatherCitiesByCoordinates(latitude, longitude, countCities)
                .let {
                    arrayCity = it
                    Log.d("qwe4", "${arrayCity.size}")
                }
        }
        return arrayCity
    }

    fun searchCityForName(cityName: String?): Int {
        var id: Int = 0
        presenterScope.launch {
            cityName?.let {
                findCityUseCase.findWeatherCityByName(cityName).let {
                    id = it.id
                }
            }
        }
        return id
    }
}