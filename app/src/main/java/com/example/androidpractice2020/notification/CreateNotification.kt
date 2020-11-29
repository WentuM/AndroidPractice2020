package com.example.androidpractice2020.notification;

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.androidpractice2020.R
import com.example.androidpractice2020.recyclerview.Song


class CreateNotification {
    val CHANNEL_ID = "channel1"

    val ACTION_PREVIUOS = "actionprevious"
    val ACTION_PLAY = "actionplay"
    val ACTION_NEXT = "actionnext"

    fun createNotif(
        context: Context,
        track: Song,
        pos: Int,
        size: Int
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManagerCompat =
                NotificationManagerCompat.from(context)
            val icon =
                BitmapFactory.decodeResource(context.resources, track.cover)
            val pendingIntentPrevious: PendingIntent?
            val drw_previous: Int
            if (pos == 0) {
                pendingIntentPrevious = null
                drw_previous = 0
            } else {
                val intentPrevious = Intent(context, NotificationActionService::class.java)
                    .setAction(ACTION_PREVIUOS)
                pendingIntentPrevious = PendingIntent.getBroadcast(
                    context, 0,
                    intentPrevious, PendingIntent.FLAG_UPDATE_CURRENT
                )
            }
            val intentPlay = Intent(context, NotificationActionService::class.java)
                .setAction(ACTION_PLAY)
            val pendingIntentPlay = PendingIntent.getBroadcast(
                context, 0,
                intentPlay, PendingIntent.FLAG_UPDATE_CURRENT
            )
            val pendingIntentNext: PendingIntent?
            val drw_next: Int
            if (pos == size) {
                pendingIntentNext = null
                drw_next = 0
            } else {
                val intentNext = Intent(context, NotificationActionService::class.java)
                    .setAction(ACTION_NEXT)
                pendingIntentNext = PendingIntent.getBroadcast(
                    context, 0,
                    intentNext, PendingIntent.FLAG_UPDATE_CURRENT
                )
            }

            //create notification
            val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Music PLayer")
                .setTicker("MPlayer")
                .setContentText("Content Text")
                .setLargeIcon(icon)
                .setOnlyAlertOnce(true) //show notification for only first time
                .setShowWhen(false)
                .addAction(android.R.drawable.ic_media_previous, "Previous", pendingIntentPrevious)
                .addAction(android.R.drawable.ic_media_play, "Play/Pause", pendingIntentPlay)
                .addAction(android.R.drawable.ic_media_next, "Next", pendingIntentNext)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build()

            notificationManagerCompat.notify(1, notification)
        }
    }
}
