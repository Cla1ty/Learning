package com.dwiariyanto.service

import android.app.IntentService
import android.content.Intent
import android.os.Handler
import android.util.Log

class MyIntentService : IntentService("MyIntentService")
{
    val handler = Handler()
    var loop = 0
    var loopCount = 30
    var id = 0

    override fun onCreate()
    {
        super.onCreate()
        Log.d(TAG, "onCreate")
    }

    override fun onDestroy()
    {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onHandleIntent(intent: Intent?)
    {
        id = ++counter
        startHandler()
        Thread.sleep(3000)
    }

    fun startHandler()
    {
        if (loop >= loopCount) return
        loop++

        Log.d(MyService.TAG, "$id Counter $loop")
        handler.postDelayed({ startHandler() }, 100)
    }

    companion object
    {
        private var counter = 0
        val TAG = MyIntentService::class.java.simpleName

        private fun getIntent(activity: MainActivity) =
                Intent(activity, MyIntentService::class.java)

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
