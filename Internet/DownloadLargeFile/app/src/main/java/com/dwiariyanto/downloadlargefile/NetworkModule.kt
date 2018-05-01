/**************************************************************************************************
 *                                                                                                *
 *                         Created by Dwi Ariyanto (creatures99@gmail.com)                        *
 *                             Copyright © 2018. All rights reserved.                             *
 *                                                                                                *
 *                                                                                                *
 * Create On:                                                                                     *
 * Thursday, April 19, 2018 at 22:17                                                              *
 *                                                                                                *
 **************************************************************************************************/

package com.dwiariyanto.downloadlargefile

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * Created by Dwi Ariyanto on 4/8/18.
 */

internal object NetworkModule
{
    val retrofit: Retrofit by lazy { createRetrofit("https://video-downloads.googleusercontent.com/") }

    private val okHttpClient: OkHttpClient by lazy {
        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)

        val interceptor = Interceptor {
            val header = it.request()
                    .newBuilder()
                    //                    .addHeader("Authorization", "Bearer ${AppPreference.instance.token}")
                    .build()

            it.proceed(header)
        }

        OkHttpClient.Builder()
                .addInterceptor(logger)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build()
    }

    private fun createRetrofit(baseUrl: String)
            : Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            //            .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
}