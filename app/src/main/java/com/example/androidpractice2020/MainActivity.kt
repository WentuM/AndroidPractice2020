package com.example.androidpractice2020

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list = list_film
        list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        list.adapter = FilmAdapter(FilmRepository().arraylist) {
            val intent = Intent(this, InfoFilmActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }
    }
}