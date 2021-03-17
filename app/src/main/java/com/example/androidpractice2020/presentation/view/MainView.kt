package com.example.androidpractice2020.presentation.view

import com.example.androidpractice2020.data.database.entity.City
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution
import moxy.viewstate.strategy.alias.Skip

@AddToEndSingle
interface MainView : MvpView {

    fun showLoading()

    fun hideLoading()

    @Skip
    fun consumeError(throwable: Throwable)

    fun navigateToDetailActivity(id: Int)

    fun getListCities(arrayCity: ArrayList<City>)

    @OneExecution
    fun navigateToLogin()
}