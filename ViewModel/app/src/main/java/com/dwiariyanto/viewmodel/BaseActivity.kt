/**************************************************************************************************
 *                                                                                                *
 *                         Created by Dwi Ariyanto (creatures99@gmail.com)                        *
 *                             Copyright © 2018. All rights reserved.                             *
 *                                                                                                *
 *                                                                                                *
 * Create On:                                                                                     *
 * Wednesday, July 04, 2018 at 22:48                                                              *
 *                                                                                                *
 **************************************************************************************************/

package com.dwiariyanto.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity

/**
 * Created by Dwi Ariyanto on 7/4/18.
 */

abstract class BaseActivity : AppCompatActivity()
{
    fun <T : ViewModel> provideViewModel(model: Class<T>) =
            ViewModelProviders
                    .of(this)
                    .get(model)
}