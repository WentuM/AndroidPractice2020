package com.example.androidpractice2020.presentation.view

import com.example.androidpractice2020.data.database.entity.City
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution
import moxy.viewstate.strategy.alias.Skip

@AddToEndSingle
interface DetailCityView: MvpView {

    @Skip
    fun consumeError(throwable: Throwable)

    fun showCityWeather(city: City)

    fun showLoading()

    fun hideLoading()

    @OneExecution
    fun navigateToLogin()
}