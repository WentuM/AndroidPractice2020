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
import com.example.androidpractice2020.ui.InfoSongActivity
import javax.security.auth.callback.Callback


class SoundService : Service() {

    val CONST_ACTION_MAIN = "ACTION_MAIN"
    val CONST_ACTION_PLAY = "ACTION_PLAY"
    val CONST_ACTION_NEXT = "ACTION_NEXT"
    val CONST_ACTION_PREV = "ACTION_PREV"
    val CONST_ACTION_STOP = "ACTION_STOP"
    private lateinit var listener: Callback
    private var id = -1
    private var song: Int? = null

    private var mediaPlayer: MediaPlayer? = null
    private var mBinder = MyBinder()


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
        if (mediaPlayer != null) {
            mediaPlayer?.stop()
        } else {
            launchForeground()
        }
        id = intent?.getIntExtra("idSong", -1)!!
        song = intent.getIntExtra("mp3", -1)

//        if (intent.action == CONST_ACTION_PLAY) {
//            playOrStopMusic(song)
//        }
        if (intent.action == CONST_ACTION_STOP) {
            mediaPlayer?.stop()
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).cancel(1)
            stopSelf()
            return START_NOT_STICKY
        }
//        if (intent?.action == CONST_ACTION_NEXT) {
//            playNextSong()
//        }
        return START_NOT_STICKY
    }

    private fun playSpecificMusic(song: Int) {
        mediaPlayer?.stop()
        mediaPlayer = MediaPlayer.create(this, song)
        mediaPlayer?.start()
    }


    fun setListener(listener: Callback) {
        this.listener = listener
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun playOrStopMusic(song: Int?) {
        Toast.makeText(
            this, "Play or Stop",
            Toast.LENGTH_SHORT
        ).show()
        if (mediaPlayer == null) {
            mediaPlayer = song?.let { MediaPlayer.create(this, it) }
            mediaPlayer?.start()
            Toast.makeText(
                this, "1",
                Toast.LENGTH_SHORT
            ).show()
        } else if (mediaPlayer != null && !mediaPlayer!!.isPlaying) {
            Toast.makeText(
                this, "2",
                Toast.LENGTH_SHORT
            ).show()
            if (song != null) {
                playSpecificMusic(song)
            }
            mediaPlayer?.start()
        } else if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            Toast.makeText(
                this, "3",
                Toast.LENGTH_SHORT
            ).show()
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


    @RequiresApi(Build.VERSION_CODES.O)
    private fun launchForeground() {

        val notificationIntent: Intent = Intent(this, InfoSongActivity::class.java)
        notificationIntent.putExtra("id", id)
        notificationIntent.action = CONST_ACTION_MAIN

        notificationIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        val previousIntent = Intent(this, SoundService::class.java)
        previousIntent.action = CONST_ACTION_PREV
        val pPreviousIntent: PendingIntent = PendingIntent.getService(this, 0, previousIntent, 0)

        val playIntent = Intent(this, SoundService::class.java)
        playIntent.action = CONST_ACTION_PLAY
        val pPlayIntent: PendingIntent = PendingIntent.getService(this, 0, playIntent, 0)

        val stopIntent = Intent(this, SoundService::class.java)
        playIntent.action = CONST_ACTION_STOP
        val pStopIntent: PendingIntent = PendingIntent.getService(this, 0, stopIntent, 0)

        val nextIntent = Intent(this, SoundService::class.java)
        nextIntent.action = CONST_ACTION_NEXT
        val pNextIntent: PendingIntent = PendingIntent.getService(this, 0, nextIntent, 0)


        val CHANNEL_ID = "my_channel_01"
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Channel title",
            NotificationManager.IMPORTANCE_DEFAULT
        )

        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
            channel
        )

        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Music PLayer")
            .setTicker("MPlayer")
            .setContentText("Content Text")
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .addAction(android.R.drawable.ic_media_previous, "Previous", pPreviousIntent)
            .addAction(android.R.drawable.ic_media_play, "Play/Pause", pPlayIntent)
            .addAction(android.R.drawable.ic_media_next, "Next", pNextIntent)
            .addAction(android.R.drawable.ic_media_pause, "Stop", pStopIntent)
            .build()

        startForeground(1, notification)
    }
}