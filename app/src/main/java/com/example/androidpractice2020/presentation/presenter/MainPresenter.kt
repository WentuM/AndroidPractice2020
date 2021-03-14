package com.example.androidpractice2020.presentation.presenter

import com.example.androidpractice2020.data.LocationRepositoryImpl
import com.example.androidpractice2020.data.database.entity.City
import com.example.androidpractice2020.domain.FindCityUseCase
import com.example.androidpractice2020.presentation.view.MainView
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope

class MainPresenter(
    private val findCityUseCase: FindCityUseCase,
    private val locationRepositoryImpl: LocationRepositoryImpl
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getListCities()
    }


    fun getListCities() {
        var longitude = 37.6156
        var latitude = 55.7522
        var arrayCity: ArrayList<City> = ArrayList()
        viewState.showLoading()
        presenterScope.launch {
            try {
                locationRepositoryImpl.getUserLocation().also {

                    arrayCity = findCityUseCase.findWeatherCitiesByCoordinates(
                        it.latitude,
                        it.longitude,
                        countCities = 15
                    )
                }
            } catch (e: NullPointerException) {
                arrayCity = findCityUseCase.findWeatherCitiesByCoordinates(
                    latitude,
                    longitude,
                    countCities = 15
                )
//            viewState.consumeError(throwable)
            } finally {
                viewState.hideLoading()
                viewState.getListCities(arrayCity)
            }
        }
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