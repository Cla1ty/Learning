package com.dwiariyanto.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class MyServiceRX : Service()
{
    var disposable: Disposable? = null

    override fun onBind(intent: Intent): IBinder?
    {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int
    {
        Log.d(MyService.TAG, "OriginService dijalankan")
        when (intent.action)
        {
            ACTION_RUN ->
            {
                disposable = Observable.empty<String>()
                        .delay(5, TimeUnit.SECONDS)
                        .subscribe({}, {}, { Log.d(MyService.TAG, "Done") })
            }
            ACTION_DISPOSE ->
            {
                disposable?.dispose()
            }
        }

        return Service.START_STICKY
    }

    override fun onCreate()
    {
        super.onCreate()
        Log.d(MyService.TAG, "onCreate")
    }

    override fun onDestroy()
    {
        super.onDestroy()
        Log.d(MyService.TAG, "onDestroy")
    }

    companion object
    {
        private var counter = 0
        val TAG = MyServiceRX::class.java.simpleName

        val ACTION_RUN = "RUN"
        val ACTION_DISPOSE = "DISPOSE"

        fun getIntent(activity: MainActivity) = Intent(activity, MyServiceRX::class.java)

        fun startService(activity: MainActivity, action: String)
        {
            val intent = getIntent(activity)
            intent.action = action
            activity.startService(intent)
        }

        fun stopService(activity: MainActivity)
        {
            val intent = getIntent(activity)
            activity.stopService(intent)
        }
    }
}
