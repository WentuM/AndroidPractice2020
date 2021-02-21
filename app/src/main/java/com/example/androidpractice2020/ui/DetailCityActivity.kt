package com.example.androidpractice2020.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.androidpractice2020.factory.InfoCity
import com.example.androidpractice2020.R
import com.example.androidpractice2020.factory.ApiFactory
import kotlinx.android.synthetic.main.activity_detail_city.*
import kotlinx.coroutines.launch

class DetailCityActivity : AppCompatActivity() {

    private val api by lazy {
        ApiFactory.weatherApi
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_city)

        val id = intent.getIntExtra("id", -1)

        loadCityWeather(id)

    }

    private fun loadCityWeather(id: Int) {
        lifecycleScope.launch {
            api.getCityById(id).let {
                city_name.text = it.name
                city_temp.text = it.main.temp.toString() + " ℃"
                city_description.text = it.weather.get(0).description
                city_icon_geo.setImageResource(R.drawable.ic_geo_foreground)
                number_humidity.text = "Влажность: ${it.main.humidity} %"

                when (it.wind.deg.toDouble()) {
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