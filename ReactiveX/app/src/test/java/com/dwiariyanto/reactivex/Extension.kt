package com.dwiariyanto.reactivex

import io.reactivex.Observable

fun <T> Observable<T>.execute()
{
    subscribe(
            {
                println("Next $it")
            },
            {
                println("Error $it")
            },
            {
                println("Complete")
            })
}
