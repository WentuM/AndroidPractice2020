package com.example.androidpractice2020.ui

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.*
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.androidpractice2020.R
import com.example.androidpractice2020.notification.CreateNotification
import com.example.androidpractice2020.notification.NotificationActionService
import com.example.androidpractice2020.recyclerview.Song
import com.example.androidpractice2020.recyclerview.SongRepository
import com.example.androidpractice2020.service.SoundService
import kotlinx.android.synthetic.main.song_info.*


class InfoSongActivity : AppCompatActivity() {
    var musicService: SoundService? = null
    var isBound = false
    var intentService: Intent? = null
    var isConnectedService = true
    lateinit var notificationManager: NotificationManager
    var id = -1
    var size = SongRepository.songList.size
    var song: Song? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.song_info)
        id = intent.getIntExtra("id", -1)
        song = id.let { it.let { it1 -> SongRepository.find(it1) } }
        song!!.cover.let {
            info_image.setImageResource(it)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
            registerReceiver(broadcastReceiver, IntentFilter("TRACKS_TRACKS"))
            startService(Intent(baseContext, NotificationActionService::class.java))
        }

        if (isConnectedService) {
            play(song!!.audio, song!!.id)
            isConnectedService = false
        }
        info_title.text = song!!.title
        info_author.text = song!!.author

        musicService?.initialiseSeekBar(info_seekBar)

        info_play.setOnClickListener {
            onTrackPlay()
        }
        info_next.setOnClickListener {
            onTrackNext()
        }
        info_prev.setOnClickListener {
            onTrackPrevious()
        }
        info_stop.setOnClickListener {
            if (isBound) {
                musicService?.stopSound()
            }
            onDestr()
            val intent = Intent(this, MainActivity::class.java)
            overridePendingTransition(R.anim.right_in, R.anim.alpha)
            startActivity(intent)
            finish()
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

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "channel1",
                "KOD Dev", NotificationManager.IMPORTANCE_LOW
            )
            notificationManager =
                getSystemService(NotificationManager::class.java)
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel)
            }
        }
    }

    var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun onReceive(context: Context?, intent: Intent) {
            val action = intent.extras!!.getString("actionname")
            when (action) {
                "actionprevious" -> onTrackPrevious()
                "actionplay" -> onTrackPlay()
                "actionnext" -> onTrackNext()
                "actioncancel" -> onDestr()
            }
        }
    }
    fun onDestr() {
        super.onDestroy()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.cancelAll()
        }
        unregisterReceiver(broadcastReceiver)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun onTrackPrevious() {
        if (isBound) {
            id -= 1
            if (id < 0) {
                id = size - 1
            }
            song = SongRepository.find(id)
            song!!.cover.let {
                info_image.setImageResource(it)
            }
            info_title.text = song!!.title
            info_author.text = song!!.author
            if (isBound) {
                musicService?.playSpecificMusic(song!!, this)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onTrackPlay() {
        if (isBound) {
            musicService?.playOrStopMusic(song, this)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onTrackNext() {
        if (isBound) {
            id += 1
            if (id >= size) {
                id = 0
            }
            song = SongRepository.find(id)
            song!!.cover.let {
                info_image.setImageResource(it)
            }
            info_title.text = song!!.title
            info_author.text = song!!.author
            if (isBound) {
                musicService?.playSpecificMusic(song!!, this)
            }
        }
    }
}
