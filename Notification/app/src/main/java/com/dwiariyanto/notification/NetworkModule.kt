/**************************************************************************************************
 *                                                                                                *
 *                         Created by Dwi Ariyanto (creatures99@gmail.com)                        *
 *                             Copyright © 2018. All rights reserved.                             *
 *                                                                                                *
 *                                                                                                *
 * Create On:                                                                                     *
 * Monday, April 30, 2018 at 22:06                                                                *
 *                                                                                                *
 **************************************************************************************************/

package com.dwiariyanto.notification

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * Created by Dwi Ariyanto on 4/30/18.
 */
internal object NetworkModule
{
    const val BASE_URL = "https://www.dropbox.com/s/"

    val retrofit: Retrofit by lazy { createRetrofit(BASE_URL) }

    private val okHttpClient: OkHttpClient by lazy {
        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)

        val interceptor = Interceptor {
            val header = it.request()
                    .newBuilder()
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
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
}
