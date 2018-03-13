package com.dwiariyanto.service

import android.app.Service
import android.content.Intent
import android.os.AsyncTask
import android.os.Handler
import android.os.IBinder
import android.util.Log

class MyService : Service()
{
    override fun onBind(intent: Intent?): IBinder
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int
    {
        Log.d(TAG, "OriginService dijalankan")
        val processAsync = ProcessAsync()
        processAsync.execute()
        Thread.sleep(3000)
        return Service.START_STICKY
    }

    override fun onDestroy()
    {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    private inner class ProcessAsync : AsyncTask<Unit, Unit, Unit>()
    {
        val handler = Handler()
        var loop = 0
        var loopCount = 30
        var id = 0

        override fun doInBackground(vararg params: Unit?)
        {
            id = ++counter

            try
            {
                startHandler()
            }
            catch (e: InterruptedException)
            {
                e.printStackTrace()
            }
        }

        override fun onPostExecute(result: Unit?)
        {
            super.onPostExecute(result)
            Log.d(TAG, "StopService")
            stopSelf()
        }

        fun startHandler()
        {
            if (loop >= loopCount) return
            loop++

            Log.d(TAG, "$id Counter $loop")
            handler.postDelayed({ startHandler() }, 100)
        }
    }

    companion object
    {
        private var counter = 0
        val TAG = MyService::class.java.simpleName

        fun getIntent(activity: MainActivity) = Intent(activity, MyService::class.java)

        fun startService(activity: MainActivity)
        {
            val intent = getIntent(activity)
            activity.startService(intent)
        }

        fun stopService(activity: MainActivity)
        {
            val intent = getIntent(activity)
            activity.stopService(intent)
        }
    }
}
