package com.example.androidpractice2020.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidpractice2020.R
import com.example.androidpractice2020.recyclerview.SongRepository
import kotlinx.android.synthetic.main.song_info.*

class InfoSongActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.song_info)
        val id = intent.extras?.getInt("id")
        val song = id?.let { SongRepository.find(it) }
        song?.cover?.let { info_image.setImageResource(it) }
        info_title.text = song?.title
        info_author.text = song?.author
    }
}
