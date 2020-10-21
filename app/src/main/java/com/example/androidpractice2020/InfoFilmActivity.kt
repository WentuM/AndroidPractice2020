package com.example.androidpractice2020

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.film_info.*

class InfoFilmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.film_info)
        val id = intent.extras?.getInt("id")
        val film = id?.let { FilmRepository().find(it) }
        if (film != null) {
            imageView.setImageResource(film.imageView)
        }
        textView6.text = id.toString()
        textView3.text = film?.name
        textView5.text = film?.text
    }
}