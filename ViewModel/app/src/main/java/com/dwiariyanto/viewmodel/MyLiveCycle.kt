/**************************************************************************************************
 *                                                                                                *
 *                         Created by Dwi Ariyanto (creatures99@gmail.com)                        *
 *                             Copyright © 2018. All rights reserved.                             *
 *                                                                                                *
 *                                                                                                *
 * Create On:                                                                                     *
 * Wednesday, July 04, 2018 at 23:00                                                              *
 *                                                                                                *
 **************************************************************************************************/

package com.dwiariyanto.viewmodel

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log

/**
 * Created by Dwi Ariyanto on 7/4/18.
 */

class MyLiveCycle : LifecycleObserver
{
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate()
    {
        Log.d("tag", "onCreate")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart()
    {
        Log.d("tag", "onStart")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop()
    {
        Log.d("tag", "onStop")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy()
    {
        Log.d("tag", "onDestroy")
    }
}