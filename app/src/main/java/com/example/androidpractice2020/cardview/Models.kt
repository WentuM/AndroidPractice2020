package com.example.androidpractice2020.cardview

import com.example.androidpractice2020.R

object Models {
    var models = arrayListOf(
        Model(R.drawable.nasa, "nasa", "The glow of the Milky Way across a sea of stars...",
            PagerAdapter(arrayListOf(R.drawable.example1, R.drawable.example2))),
        Model(R.drawable.spacex, "spacex", "We are the in the World",
            PagerAdapter(arrayListOf(R.drawable.example3, R.drawable.exampl4)))
    )
}