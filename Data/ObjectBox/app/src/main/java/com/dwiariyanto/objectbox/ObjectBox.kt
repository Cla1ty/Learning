package com.dwiariyanto.objectbox

import android.content.Context
import io.objectbox.BoxStore


/**
 * Created by Dwi Ariyanto on 20/03/19.
 */

object ObjectBox {
    private lateinit var boxStore: BoxStore

    fun init(context: Context) {
        boxStore = MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()
    }

    fun get(): BoxStore {
        return boxStore
    }
}

fun runInTx(run: () -> Unit) {
    ObjectBox.get().runInTx(run)
}