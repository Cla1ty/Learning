/**************************************************************************************************
 * *
 * Created by Dwi Ariyanto (creatures99@gmail.com)                        *
 * Copyright © 2018. All rights reserved.                             *
 * *
 * *
 * Create On:                                                                                     *
 * Wednesday, July 04, 2018 at 01:06                                                              *
 * *
 */

package com.dwiariyanto.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.SystemClock
import java.util.*

/**
 * Created by Dwi Ariyanto on 7/4/18.
 */
class ViewModel2 : ViewModel()
{
    private val ONE_SECOND = 1000
    var mElapsedTime = MutableLiveData<Long>()
        private set
    private var mInitialTime: Long = 0

    fun LiveDataTimerViewModel()
    {
        if (mInitialTime == 0L)
            mInitialTime = SystemClock.elapsedRealtime()
        val timer = Timer()

        timer.scheduleAtFixedRate(object : TimerTask()
        {
            override fun run()
            {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
                mElapsedTime.postValue(newValue)
            }
        }, ONE_SECOND.toLong(), ONE_SECOND.toLong())

    }
}
