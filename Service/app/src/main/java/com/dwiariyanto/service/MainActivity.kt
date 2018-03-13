package com.dwiariyanto.service

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonRunningService()

        buttonStartService()
        buttonStopService()

        buttonStartIntentService()
        buttonStopIntentService()

        buttonStartServiceRX()
        buttonStopServiceRX()
        buttonDisposeServiceRX()

        buttonStartIntentServiceRX()
        buttonStopIntentServiceRX()
        buttonDisposeIntentServiceRX()
    }

    private fun buttonStopIntentService()
    {
        btn4.setOnClickListener { MyIntentService.stopService(this) }
    }

    private fun buttonStartIntentService()
    {
        btn3.setOnClickListener { MyIntentService.startService(this) }
    }

    private fun buttonRunningService()
    {
        btn0.setOnClickListener { getRunningService() }
    }

    private fun buttonStopService()
    {
        btn2.setOnClickListener { MyService.stopService(this) }
    }

    private fun buttonStartService()
    {
        btn1.setOnClickListener { MyService.startService(this) }
    }

    private fun buttonStopServiceRX()
    {
        btn6.setOnClickListener { MyServiceRX.stopService(this) }
    }

    private fun buttonStartServiceRX()
    {
        btn5.setOnClickListener { MyServiceRX.startService(this, MyServiceRX.ACTION_RUN) }
    }

    private fun buttonDisposeServiceRX()
    {
        btn9.setOnClickListener { MyServiceRX.startService(this, MyServiceRX.ACTION_DISPOSE) }
    }

    private fun buttonStopIntentServiceRX()
    {
        btn8.setOnClickListener { MyIntentServiceRX.stopService(this) }
    }

    private fun buttonStartIntentServiceRX()
    {
        btn7.setOnClickListener { MyIntentServiceRX.startService(this, MyIntentServiceRX.ACTION_RUN) }
    }

    private fun buttonDisposeIntentServiceRX()
    {
        btn10.setOnClickListener { MyIntentServiceRX.startService(this, MyIntentServiceRX.ACTION_DISPOSE) }
    }

    private fun getRunningService()
    {
        val am = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val rs = am.getRunningServices(50)

        rs.forEach {
            Log.i("Service", "Process " + it.process +
                             " \nwith component " + it.service.className +
                             " \n" + it.started)
        }
    }

}
