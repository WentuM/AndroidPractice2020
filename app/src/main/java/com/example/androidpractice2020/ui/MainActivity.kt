package com.example.androidpractice2020.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.size
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidpractice2020.R
import com.example.androidpractice2020.recyclerview.SongRepository.songList
import com.example.androidpractice2020.recyclerview.SongAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list = song_list
        list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        list.adapter =
            SongAdapter(songList) {
                val intent = Intent(this, InfoSongActivity::class.java)
                intent.putExtra("id", it)
                intent.putExtra("listSize", songList.size)
                startActivity(intent)
                finish()
            }

    }
}