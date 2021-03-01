package com.example.androidpractice2020.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.androidpractice2020.R
import com.example.androidpractice2020.application.WeatherApplication
import com.example.androidpractice2020.data.api.ApiFactory
import com.example.androidpractice2020.domain.FindCityUseCase
import kotlinx.android.synthetic.main.activity_detail_city.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailCityActivity : AppCompatActivity() {

    private val api by lazy {
        ApiFactory.weatherApi
    }
    private lateinit var findCityUseCase: FindCityUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_city)

        val id = intent.getIntExtra("id", -1)
        findCityUseCase = FindCityUseCase((application as WeatherApplication).repository, Dispatchers.IO)
        loadCityWeather(id)

    }

    private fun loadCityWeather(id: Int) {
        lifecycleScope.launch {
            findCityUseCase.findWeatherCityById(id).let {
                city_name.text = it.name
                city_temp.text = it.temp.toString() + " ℃"
                city_description.text = it.description
                city_icon_geo.setImageResource(R.drawable.ic_geo_foreground)
                number_humidity.text = "Влажность: ${it.numbHumidity} %"

                when (it.deg.toDouble()) {
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
        }
    }
}