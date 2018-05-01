/**************************************************************************************************
 *                                                                                                *
 *                         Created by Dwi Ariyanto (creatures99@gmail.com)                        *
 *                             Copyright © 2018. All rights reserved.                             *
 *                                                                                                *
 *                                                                                                *
 * Create On:                                                                                     *
 * Wednesday, April 25, 2018 at 00:12                                                             *
 *                                                                                                *
 **************************************************************************************************/

package com.dwiariyanto.downloadlargefile

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Streaming

/**
 * Created by Dwi Ariyanto on 4/25/18.
 */

interface TestApi{
    @Streaming
    @GET("AH4gaR-WDYZzwnsVwYaOrZGiR7KX8RVK7xunptvM8mlbYLi82iUnA6OmzriZujqmy5XQ2-1QQKItJyI33zYuP486Hhcju93wQ8HKi27pCepsbhqpFWDIl65J0RXM0cIiPymIg7vRhzHt0RWgDfK3Em4YacFT4LUalanJN93kVcSreo_ado68QcpGtgONblcuWSS5si8wOERQxxN_1os1snwiPO-4sw-3xzNxpGaqkqnS4_9SSH-SITufAPJwlusTefu737IVQ91AFtqW2rujhUEFJFrgG3UEGVkG-h2sWh2gaPw2WhRWfpAyZpNBfLH3_OmSY4c62vjYgmyqF52xgapMrksznCHlSQNIMS_pyP73wN0ViJtGllAnbLrYlarhhqeVMiXsBcybtEA4RMwVYeq2f62SNYUyO9owqNKPayqzLm8gH2WXgwzMHMPEb83m6nX75ED5jI4ETh7EiD-J6w33Chzh8xAoRDQbGiOgZUDQ0LYg3n8tGr_BeeyTPK5QImw1UDfSvXdlttfev4xEyrhlv7iEQGMa-9CV234a9mvmNM31eY8j60CGY7S8zSBnxjGJtPHfkUy5YXcV9oELIqwJTLmQlZWHPpvdr1uf-dkptypHktaRo1ISPlomg6Fps6NGQFK75Qplm9HKytFIHylblyUcJjgQNqttF1XwOcD2BKZrItlDyZPewsvsv3Q6zDtKqbbqA_gNglWuKvg_Lzr7iWqgK_cESw")
    fun download(): Call<ResponseBody>


    @Streaming
    @GET("AH4gaR97ZZBq-OBc6PY4NLVAykZKMjU1t03h54tZ4QOE6-mbfPYUB0iAgVbB7nhukHqOiVOp_lCplFZGJMVTnjrNO0tYu569_HNovPgb4eSCL9bl25PAZi3uikGRT1cxuLuITHf4ZqIi-nmbVJG_-qylz5fnM4QkUrXG7ct1YlPW3s3gOnDo76R2II6ZG-uapCB5GOnhWihCgpVIhT5qikCmEHpvTIhR82LB-TycwZufZDKTHSJyvCGxs1-HCFN7kkWsDGSJav8JKmu-dlEGlkiN7vhEdQsFuJqGpmZiS_Rl2vw1-Kc4krYWBBjv76tGOujWrXa8VZelYJ7ajle8Jj8NwKB9EU26OonbTugPlIlkoz-ydABDNiqjcNK6LM3hB_3RrY2CM8S85F1VJrMUrctwlH9eM4lc6KrLet9JJ3-uZ2DWE5KDZ9GxHSE6Kvb1TSPFHH6NKtN9F6t3WYxlVyLcenUFrwSPwdyXJ3vgpL_OWXRL4YHQ9kTTU3yoGe-eLHcnbLoa8Lf07G88yJ0RrYkBqvGkRvY81xzpcqXEUUPKSw7J8tx5Q9sMN-8vQznMhz7wyWwBQj11YMfW5623x1ZWoOd4Jt9AGBuN7x0SnRUxvzw5XeumeF0Ste4ZEa9m_pI3xV7xNRfXmdiVjn7qPKKU326KyeCitTjzRZsQAahQ2MdCtzxuac-f-5PXljlOm3G08eozhAB5pStCdRYNUd_c7YolWQ3lkA")
    fun downloadMedium(): Observable<ResponseBody>

    companion object
    {
        val instance = NetworkModule.retrofit.create(TestApi::class.java)
    }
}