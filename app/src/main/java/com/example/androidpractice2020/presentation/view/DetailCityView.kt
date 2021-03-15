package com.example.androidpractice2020.presentation.view

import com.example.androidpractice2020.data.database.entity.City
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution
import moxy.viewstate.strategy.alias.Skip

@AddToEndSingle
interface DetailCityView : MvpView {

    @Skip
    fun consumeError(throwable: Throwable)

    fun setNameCity(name: String)

    fun setTempCity(temp: Double)

    fun setDescrCity(description: String)

    fun setHumidityCity(humidity: Int)

    fun setDegCity(deg: Int)

    fun setIconCity()


    fun showLoading()

    fun hideLoading()

    @OneExecution
    fun navigateToLogin()
}