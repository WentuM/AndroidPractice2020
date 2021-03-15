package com.example.androidpractice2020.presentation.presenter

import com.example.androidpractice2020.domain.FindCityUseCase
import com.example.androidpractice2020.presentation.view.DetailCityView
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope

class DetailCityPresenter(
    private val findCityUseCase: FindCityUseCase
) : MvpPresenter<DetailCityView>() {

    fun loadCityWeather(id: Int) {
        viewState.showLoading()
        presenterScope.launch {
            findCityUseCase.findWeatherCityById(id).let {
                try {
                    viewState.apply {
                        setNameCity(it.name)
                        setDescrCity(it.description)
                        setDegCity(it.deg)
                        setHumidityCity(it.numbHumidity)
                        setIconCity()
                        setTempCity(it.temp)
                    }
                } catch (throwable: Throwable) {
                    viewState.consumeError(throwable)
                } finally {
                    viewState.hideLoading()
                }
            }
        }
    }
}