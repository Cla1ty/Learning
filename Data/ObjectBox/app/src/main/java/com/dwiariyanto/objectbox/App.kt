package com.dwiariyanto.objectbox

import android.app.Application

/**
 * Created by Dwi Ariyanto on 20/03/19.
 */

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        ObjectBox.init(this)
    }
}