package com.dwiariyanto.view

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart()
    {
        super.onStart()

        val image = ImageView(this)
        val context = this as Context

        Log.d("TAG", "IMAGE " + (image.context is ImageView))
        Log.d("TAG", "IMAGE " + (image.context is MainActivity))
        Log.d("TAG", "IMAGE " + (image.context is Application))
        Log.d("TAG", "========================")
        Log.d("TAG", "APP " + (applicationContext is ImageView))
        Log.d("TAG", "APP " + (applicationContext is MainActivity))
        Log.d("TAG", "APP " + (applicationContext is Application))
        Log.d("TAG", "========================")
        Log.d("TAG", "BASE " + (baseContext is ImageView))
        Log.d("TAG", "BASE " + (baseContext is MainActivity))
        Log.d("TAG", "BASE " + (baseContext is Application))
        Log.d("TAG", "========================")
        Log.d("TAG", "CONTEXT " + (context is ImageView))
        Log.d("TAG", "CONTEXT " + (context is MainActivity))
        Log.d("TAG", "CONTEXT " + (context is Application))
        Log.d("TAG", "========================")
        Log.d("TAG", "BASE " + (context == image.context))
        Log.d("TAG", "BASE " + (context == editText.context))
        Log.d("TAG", "BASE " + (baseContext == editText.context))


        //        TimeAnimator()
//                .also {
//                    it.setTimeListener { animation, totalTime, deltaTime ->
//                        Log.d("ANIM", " = $totalTime $deltaTime") }
//                }
//                .start()
    }
}
