/**************************************************************************************************
 *                                                                                                *
 *                         Created by Dwi Ariyanto (creatures99@gmail.com)                        *
 *                             Copyright © 2018. All rights reserved.                             *
 *                                                                                                *
 *                                                                                                *
 * Create On:                                                                                     *
 * Monday, April 30, 2018 at 22:06                                                                *
 *                                                                                                *
 **************************************************************************************************/

package com.dwiariyanto.downloadlargefile

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import java.io.*

class DownloadService : Service()
{
    private val TAG = "tag"
    private var currentProgress = 0
    private var currentTime = 0L

    private var disposable: Disposable? = null

    override fun onBind(intent: Intent?): IBinder
    {
        TODO()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    {
        when (intent?.action)
        {
            ACTION_DOWNLOAD ->
            {
                downloadMedium()
            }
            ACTION_DOWNLOAD_CANCEL ->
            {
                disposable?.dispose()
                Log.e(TAG, "CANCEL $disposable")
                cancel()
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun downloadMedium()
    {
        disposable = TestApi.instance.downloadMedium().subscribeOn(Schedulers.io())
                .flatMap { data ->
                    Observable.create<Int> {
                        writeResponseBodyToDisk(data,
                                { progress, currentDownload, sizeDownload ->
                                    Log.d(TAG, "DISPOSE = ${disposable?.isDisposed}")
                                    it.onNext((currentDownload / 142600).toInt())
                                },
                                {
                                    it.onComplete()
                                })
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { progress ->
                            if (currentProgress == progress) return@subscribe

                            currentProgress = progress
                            Log.d(TAG, "PROGRESS : $progress")

                            if (currentTime + 1000 < System.currentTimeMillis())
                            {
                                currentTime = System.currentTimeMillis()
                                showNotification(progress)
                            }
                        },
                        {
                            Log.e(TAG, "error")
                        },
                        {
                            complete()
                        })
    }

    private fun writeResponseBodyToDisk(
            body: ResponseBody,
            onProgress: (
                    progress: Float,
                    currentDownload: Long,
                    sizeDownload: Long
            ) -> Unit,
            onComplete: () -> Unit
    ): Boolean
    {
        try
        {
            // todo change the file location/name according to your needs
            val futureStudioIconFile = File(Environment.getExternalStorageDirectory(),
                    "test_download.${body.contentType()?.subtype()}")

            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null

            try
            {
                val fileReader = ByteArray(4096)

                var fileSizeDownloaded: Long = 0

                inputStream = body.byteStream()
                outputStream = FileOutputStream(futureStudioIconFile)

                while (true)
                {
                    val read = inputStream!!.read(fileReader)

                    if (read == -1)
                    {
                        break
                    }

                    outputStream.write(fileReader, 0, read)

                    fileSizeDownloaded += read.toLong()
                    val totalSize = body.contentLength()
                    val percentage = fileSizeDownloaded / (totalSize.toFloat())

                    onProgress.invoke(percentage, fileSizeDownloaded, totalSize)
                }

                outputStream.flush()
                onComplete.invoke()
                return true
            }
            catch (e: IOException)
            {
                return false
            }
            finally
            {
                inputStream?.close()
                outputStream?.close()
            }
        }
        catch (e: IOException)
        {
            return false
        }

    }

    val notificationManager by lazy {
        val notif = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notifyID = 1

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val CHANNEL_ID = "my_channel_01" // The id of the channel.
            val name = "Ini channel" // The user-visible name of the channel.
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            notif.createNotificationChannel(mChannel)
        }
        notif
    }
    val mBuilder by lazy {
        val intent = Intent(this, DownloadService::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        intent.action = ACTION_DOWNLOAD_CANCEL

        val bundle = Bundle()
        bundle.putBoolean("CANCEL", true)

        intent.putExtra("CANCEL", true)
        val cancelIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            PendingIntent.getForegroundService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        else
            PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        NotificationCompat.Builder(this, "0")
                .setContentTitle("Picture Download")
                .setContentText("Download in progress")
                .addAction(R.drawable.notification_icon_background,
                        "Cancel",
                        cancelIntent)
                .setWhen(0)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setPriority(NotificationCompat.PRIORITY_MAX)
    }

    private fun showNotification(currentProgress: Int = 0)
    {
        val PROGRESS_MAX = 100
        val PROGRESS_CURRENT = currentProgress
        mBuilder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false)
        notificationManager.notify(0, mBuilder.build())
    }

    private fun complete()
    {
        val mBuilder = NotificationCompat.Builder(this, "0")
        mBuilder.setContentTitle("Picture Download")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .priority = NotificationCompat.PRIORITY_LOW

        mBuilder.setContentText("Download complete")
                .setProgress(0, 0, false)
        notificationManager.notify(0, mBuilder.build())
    }

    private fun cancel()
    {
        val mBuilder = NotificationCompat.Builder(this, "0")
        mBuilder.setContentTitle("Picture Download")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .priority = NotificationCompat.PRIORITY_LOW

        mBuilder.setContentText("Download Cancel")
                .setProgress(0, 0, false)
        notificationManager.notify(0, mBuilder.build())
    }

    companion object
    {
        val ACTION_DOWNLOAD = "A1"
        val ACTION_DOWNLOAD_CANCEL = "A2"

        fun download(context: Context)
        {
            val intent = Intent(context, DownloadService::class.java).apply {
                action = ACTION_DOWNLOAD
            }
            context.startService(intent)
        }

        fun cancel(context: Context)
        {
            val intent = Intent(context, DownloadService::class.java).apply {
                action = ACTION_DOWNLOAD_CANCEL
            }
            context.startService(intent)
        }

    }
}
