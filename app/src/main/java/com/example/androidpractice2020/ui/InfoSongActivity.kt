package com.example.androidpractice2020.ui

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.androidpractice2020.R
import com.example.androidpractice2020.recyclerview.SongRepository
import com.example.androidpractice2020.service.SoundService
import kotlinx.android.synthetic.main.song_info.*


class InfoSongActivity : AppCompatActivity() {
    var mHandler: Handler = Handler()
    private var mediaPlayer: MediaPlayer? = null
    var musicService: SoundService? = null
    var isBound = false
    var intentService: Intent? = null
    var isConnectedService = true

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.song_info)
        var id = intent.getIntExtra("id", -1)
        var size = intent.getIntExtra("listSize", -1)
        val song = id.let { it.let { it1 -> SongRepository.find(it1) } }
        song.cover.let {
            info_image.setImageResource(it)
        }
        if (isConnectedService) {
            play(song.audio, song.id)
            isConnectedService = false
        }
        info_title.text = song.title
        info_author.text = song.author

        musicService?.initialiseSeekBar(info_seekBar)

        info_play.setOnClickListener {
            if (isBound) {
                musicService?.playOrStopMusic(song.audio)
            }
        }
        info_next.setOnClickListener {
            if (isBound) {
                id += 1
                if (id >= size) {
                    id = 0
                }
                val intent = Intent(this, InfoSongActivity::class.java)
                intent.putExtra("id", id)
                intent.putExtra("listSize", size)
                overridePendingTransition(R.anim.right_in, R.anim.alpha)
                startActivity(intent)
                finish()
            }
        }
        info_prev.setOnClickListener {
            if (isBound) {
                if (isBound) {
                    id -= 1
                    if (id < 0) {
                        id = size - 1
                    }
                    val intent = Intent(this, InfoSongActivity::class.java)
                    intent.putExtra("id", id)
                    intent.putExtra("listSize", size)
                    overridePendingTransition(R.anim.right_in, R.anim.alpha)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder: SoundService.MyBinder = service as SoundService.MyBinder
            musicService = binder.service
            isBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            isBound = false
        }
    }

    override fun onStart() {
        bindService(intentService, connection, 0)
        super.onStart()
    }

    fun play(song: Int?, id: Int?) {
        intentService = Intent(this, SoundService::class.java)
        intentService?.putExtra("mp3", song)
        intentService?.putExtra("idSong", id)
        startService(intentService)
    }
}
