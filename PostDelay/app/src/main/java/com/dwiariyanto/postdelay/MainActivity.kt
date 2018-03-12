package com.dwiariyanto.postdelay

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    private var counter = 0
    private val runnable = Runnable{
        result.text = "Counter ${++counter}"
    }

    private val rootView: View
        get() = findViewById<View>(android.R.id.content)

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupButton()
    }

    private fun setupButton()
    {
        buttonRun()
        buttonDispose()

        buttonRunTimer()
        buttonDisposeTimer()
    }

    private fun buttonDispose()
    {
        btn2.setOnClickListener {
            rootView.removeCallbacks(runnable)
        }
    }

    private fun buttonRun()
    {
        btn1.setOnClickListener {
            rootView.postDelayed(runnable, 2000)
        }
    }

    private fun buttonRunTimer()
    {
        btn3.setOnClickListener {
            runTimer()
        }
    }

    private fun runTimer()
    {
        rootView.postDelayed(runnableTimer, 100)
    }

    private val runnableTimer = Runnable{
        result.text = "Counter ${++counter}"
        runTimer()
    }

    private fun buttonDisposeTimer(){
        btn4.setOnClickListener {
            rootView.removeCallbacks(runnableTimer)
        }
    }

}
