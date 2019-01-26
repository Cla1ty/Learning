/**************************************************************************************************
 *                                                                                                *
 *                         Created by Dwi Ariyanto (creatures99@gmail.com)                        *
 *                             Copyright © 2018. All rights reserved.                             *
 *                                                                                                *
 *                                                                                                *
 * Create On:                                                                                     *
 * Sunday, April 29, 2018 at 12:03                                                                *
 *                                                                                                *
 **************************************************************************************************/

package com.dwiariyanto.notification

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    private val CHANNEL_ID = "1"
    private val notificationId = 1
    private val timer by lazy { Timer() }

    val notificationManager by lazy { NotificationManagerCompat.from(this) }
    val mBuilder by lazy {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP

        val bundle = Bundle()
        bundle.putBoolean("CANCEL", true)

        intent.putExtra("CANCEL", true)
        val cancelIntent = PendingIntent.getActivity(this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT)

        NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Picture Download")
                .setContentText("Download in progress")
                .addAction(R.drawable.notification_icon_background, "Cancel", cancelIntent)
                .setWhen(0)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setPriority(NotificationCompat.PRIORITY_MAX)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent.hasExtra("CANCEL") && intent.getBooleanExtra("CANCEL", false))
        {
            timer.cancel()
            cancel()
        }

        btnDownload.setOnClickListener {
            timer.start()
        }

    }

    private inner class Timer(val future: Long = 100000) : CountDownTimer(future, 2000)
    {
        override fun onFinish()
        {
            complete()
        }

        override fun onTick(millisUntilFinished: Long)
        {
                showNotification(((future - millisUntilFinished) / 1000).toInt())
        }
    }

    private fun showNotification(currentProgress: Int = 0)
    {
        val PROGRESS_MAX = 100
        val PROGRESS_CURRENT = currentProgress
        mBuilder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false)
        notificationManager.notify(notificationId, mBuilder.build())
    }

    private fun complete()
    {
        val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
        mBuilder.setContentTitle("Picture Download")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .priority = NotificationCompat.PRIORITY_LOW

        mBuilder.setContentText("Download complete")
                .setProgress(0, 0, false)
        notificationManager.notify(notificationId, mBuilder.build())
    }

    private fun cancel()
    {
        val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
        mBuilder.setContentTitle("Picture Download")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .priority = NotificationCompat.PRIORITY_LOW

        mBuilder.setContentText("Download Cancel")
                .setProgress(0, 0, false)
        notificationManager.notify(notificationId, mBuilder.build())
    }
}
