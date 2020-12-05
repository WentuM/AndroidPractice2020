package com.example.androidpractice2020.notification;

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.androidpractice2020.R
import com.example.androidpractice2020.recyclerview.Song
import com.example.androidpractice2020.ui.InfoSongActivity


object CreateNotification {
    val CHANNEL_ID: String
        get() = "channel1"

    val ACTION_PREVIUOS = "actionprevious"
    val ACTION_PLAY = "actionplay"
    val ACTION_NEXT = "actionnext"
    val ACTION_MAIN = "actionmain"
    val ACTION_CANCEL = "actioncancel"

    fun createNotif(
        context: Context,
        track: Song,
        id: Int
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val notificationIntent: Intent = Intent(context, InfoSongActivity::class.java)
            notificationIntent.putExtra("id", id)
            notificationIntent.action = ACTION_MAIN

            notificationIntent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            val pendingIntent: PendingIntent =
                PendingIntent.getActivity(
                    context,
                    0,
                    notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )

            val intentCancel = Intent(context, NotificationActionService::class.java)
                .setAction(ACTION_CANCEL)
            val pendingIntentCancel = PendingIntent.getBroadcast(
                context, 0,
                intentCancel, PendingIntent.FLAG_UPDATE_CURRENT
            )

            val notificationManagerCompat =
                NotificationManagerCompat.from(context)
            val icon =
                BitmapFactory.decodeResource(context.resources, track.cover)
            val pendingIntentPrevious: PendingIntent?

            val intentPrevious = Intent(context, NotificationActionService::class.java)
                .setAction(ACTION_PREVIUOS)
            pendingIntentPrevious = PendingIntent.getBroadcast(
                context, 0,
                intentPrevious, PendingIntent.FLAG_UPDATE_CURRENT
            )
            val intentPlay = Intent(context, NotificationActionService::class.java)
                .setAction(ACTION_PLAY)
            val pendingIntentPlay = PendingIntent.getBroadcast(
                context, 0,
                intentPlay, PendingIntent.FLAG_UPDATE_CURRENT
            )
            val pendingIntentNext: PendingIntent?
            val intentNext = Intent(context, NotificationActionService::class.java)
                .setAction(ACTION_NEXT)
            pendingIntentNext = PendingIntent.getBroadcast(
                context, 0,
                intentNext, PendingIntent.FLAG_UPDATE_CURRENT
            )

            //create notification
            val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(track.title)
                .setTicker("MPlayer")
                .setContentText(track.author)
                .setLargeIcon(icon)
                .setOngoing(true)
                .setContentIntent(pendingIntent)
                .addAction(android.R.drawable.ic_media_previous, "Previous", pendingIntentPrevious)
                .addAction(android.R.drawable.ic_media_play, "Play/Pause", pendingIntentPlay)
                .addAction(android.R.drawable.ic_media_next, "Next", pendingIntentNext)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build()

            notificationManagerCompat.notify(1, notification)
        }
    }
}
