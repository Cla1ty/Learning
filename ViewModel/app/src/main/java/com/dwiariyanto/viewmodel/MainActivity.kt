/**************************************************************************************************
 *                                                                                                *
 *                         Created by Dwi Ariyanto (creatures99@gmail.com)                        *
 *                             Copyright © 2018. All rights reserved.                             *
 *                                                                                                *
 *                                                                                                *
 * Create On:                                                                                     *
 * Wednesday, July 04, 2018 at 00:38                                                              *
 *                                                                                                *
 **************************************************************************************************/

package com.dwiariyanto.viewmodel

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.os.SystemClock
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel1()
        viewModel2()

        lifecycle.addObserver(MyLiveCycle())
    }

    private fun viewModel1()
    {
        val chronometerViewModel = provideViewModel(ViewModel1::class.java)
                .apply {
                    if (startTime == 0L)
                        startTime = SystemClock.elapsedRealtime()
                }

        chronometer.apply {
            base = chronometerViewModel.startTime
            start()
        }
    }

    private fun viewModel2()
    {
        val viewModel2 = provideViewModel(ViewModel2::class.java)
        viewModel2.LiveDataTimerViewModel()
        viewModel2.mElapsedTime.observe(this, Observer {
            timer.text = "time : $it"
        })
    }
}
