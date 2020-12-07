package com.example.androidpractice2020.recyclerview

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

data class Song (
    val title: String, @DrawableRes val cover: Int, @RawRes val audio: Int, val author: String, val id : Int)
