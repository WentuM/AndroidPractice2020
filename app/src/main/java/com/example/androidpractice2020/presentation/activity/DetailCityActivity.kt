package com.example.androidpractice2020.presentation.activity

import android.os.Bundle
import android.view.View
import com.example.androidpractice2020.R
import com.example.androidpractice2020.application.WeatherApplication
import com.example.androidpractice2020.data.database.entity.City
import com.example.androidpractice2020.domain.FindCityUseCase
import com.example.androidpractice2020.presentation.presenter.DetailCityPresenter
import com.example.androidpractice2020.presentation.view.DetailCityView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail_city.*
import kotlinx.coroutines.Dispatchers
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class DetailCityActivity : MvpAppCompatActivity(), DetailCityView {

    private var id = 0

    @InjectPresenter
    lateinit var presenter: DetailCityPresenter

    @ProvidePresenter
    fun providePresenter(): DetailCityPresenter = initPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_city)

        id = intent.getIntExtra("id", -1)

        presenter.loadCityWeather(id)
    }

    private fun compareDegCity(deg: Int) {
        when (deg.toDouble()) {
            in 22.5..67.5 -> direction_wind.text = "Направление ветра: CB"
            in 112.5..157.5 -> direction_wind.text = "Направление ветра: ЮВ"
            in 202.5..247.5 -> direction_wind.text = "Направление ветра: ЮЗ"
            in 292.5..337.5 -> direction_wind.text = "Направление ветра: CЗ"
            in 337.5..360.0, in 0.0..22.5 -> direction_wind.text = "Направление ветра: C"
            in 67.5..112.5 -> direction_wind.text = "Направление ветра: В"
            in 157.5..202.5 -> direction_wind.text = "Направление ветра: Ю"
            in 247.5..292.5 -> direction_wind.text = "Направление ветра: З"
            else -> "Zero"
        }
    }

    private fun initPresenter() = DetailCityPresenter(
        findCityUseCase = FindCityUseCase(
            (application as WeatherApplication).repository, Dispatchers.IO
        )
    )

    override fun consumeError(throwable: Throwable) {
        throwable.message?.let {
            Snackbar.make(
                findViewById(android.R.id.content),
                it,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    override fun setNameCity(name: String) {
        city_name.text = name

    }

    override fun setTempCity(temp: Double) {
        city_temp.text = temp.toString() + " ℃"

    }

    override fun setDescrCity(description: String) {
        city_description.text = description
    }

    override fun setHumidityCity(humidity: Int) {
        number_humidity.text = "Влажность: ${humidity} %"

    }

    override fun setDegCity(deg: Int) {
        compareDegCity(deg)
    }

    override fun setIconCity() {
        city_icon_geo.setImageResource(R.drawable.ic_geo_foreground)
    }

    override fun showLoading() {
        progress_list.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_list.visibility = View.GONE
    }

    override fun navigateToLogin() {}
}