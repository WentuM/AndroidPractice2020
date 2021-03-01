package com.example.androidpractice2020.data.api.response

import com.example.androidpractice2020.data.City

data class CitiesWeatherResponse(
    val cod: String,
    val count: Int,
    val list: ArrayList<InfoCity>,
    val message: String
)

data class InfoCity(
    val clouds: Clouds,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val rain: Any,
    val snow: Any,
    val sys: Sys,
    val weather: List<Weather>,
    val wind: Wind
)

