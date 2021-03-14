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
                    viewState.showCityWeather(it)
                } catch (throwable: Throwable) {
                    viewState.consumeError(throwable)
                }
                finally {
                    viewState.hideLoading()
                }
            }
        }
    }
}