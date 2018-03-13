package com.dwiariyanto.service

import android.app.IntentService
import android.content.Intent
import android.util.Log
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class MyIntentServiceRX : IntentService("MyIntentServiceRX")
{
    var disposable: Disposable? = null

    override fun onHandleIntent(intent: Intent?)
    {
        Log.d(MyService.TAG, "OriginService dijalankan")
        when (intent!!.action)
        {
            MyServiceRX.ACTION_RUN ->
            {
                disposable = Observable.empty<String>()
                        .delay(5, TimeUnit.SECONDS)
                        .subscribe({}, {}, { Log.d(MyService.TAG, "Done") })
            }
            MyServiceRX.ACTION_DISPOSE ->
            {
                disposable?.dispose()
            }
        }
    }

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

    companion object
    {
        private var counter = 0
        val TAG = MyIntentServiceRX::class.java.simpleName

        val ACTION_RUN = "RUN"
        val ACTION_DISPOSE = "DISPOSE"

        fun getIntent(activity: MainActivity) = Intent(activity, MyIntentServiceRX::class.java)

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
