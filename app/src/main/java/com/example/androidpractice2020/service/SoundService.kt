package com.example.androidpractice2020.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.androidpractice2020.R
import com.example.androidpractice2020.notification.CreateNotification
import com.example.androidpractice2020.recyclerview.Song
import com.example.androidpractice2020.ui.InfoSongActivity
import javax.security.auth.callback.Callback


class SoundService : Service() {

    private var mediaPlayer: MediaPlayer? = null
    private var mBinder = MyBinder()
    private var song: Song? = null


    inner class MyBinder : Binder() {
        internal
        val service: SoundService
            get() = this@SoundService
    }

    override fun onBind(p0: Intent?): IBinder? {
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(
            this, "Служба создана",
            Toast.LENGTH_SHORT
        ).show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }

    fun playSpecificMusic(song: Song, context: Context) {
        mediaPlayer?.stop()
        mediaPlayer = MediaPlayer.create(this, song.audio)
        this.song = song
        CreateNotification.createNotif(
            context, song, song.id
        )
        mediaPlayer?.start()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun playOrStopMusic(song: Song?, context: Context) {
        if (mediaPlayer == null) {
            if (song != null) {
                mediaPlayer = song.audio.let { MediaPlayer.create(this, it) }
            }

            if (song != null) {
                CreateNotification.createNotif(
                    context, song, song.id
                )
            }

            this.song = song
            mediaPlayer?.start()
        } else if (mediaPlayer != null && !mediaPlayer!!.isPlaying) {

            if (this.song != song) {

                if (song != null) {
                    playSpecificMusic(song, context)
                }
            } else {
                mediaPlayer?.start()
            }
        } else if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            mediaPlayer?.let {
                it.pause()
            }
        }
    }

    fun stopSound() {
        if (mediaPlayer != null) {
            mediaPlayer?.stop()
            stopSelf()
        }
    }

    fun initialiseSeekBar(seekBar: SeekBar) {

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p2) {
                    mediaPlayer?.seekTo(p1)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
        seekBar.max = mediaPlayer!!.duration

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    seekBar.progress = mediaPlayer!!.currentPosition
                    handler.postDelayed(this, 1000)
                } catch (e: Exception) {
                    seekBar.progress = 0
                }
            }

        }, 0)
    }
}