/**************************************************************************************************
 *                                                                                                *
 *                         Created by Dwi Ariyanto (creatures99@gmail.com)                        *
 *                             Copyright © 2018. All rights reserved.                             *
 *                                                                                                *
 *                                                                                                *
 * Create On:                                                                                     *
 * Sunday, April 29, 2018 at 15:36                                                                *
 *                                                                                                *
 **************************************************************************************************/

package com.dwiariyanto.notification

import android.app.IntentService
import android.content.Context
import android.content.Intent

class DownloadService : IntentService("DownloadService")
{

    override fun onHandleIntent(intent: Intent?)
    {
        when (intent?.action)
        {
            ACTION_DOWNLOAD ->
            {

            }
            ACTION_DOWNLOAD_CANCEL ->
            {

            }
        }
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
