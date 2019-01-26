package com.dwiariyanto.glide

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface ImageApi{
		@get:GET("wp-content/uploads/2017/07/13225736/kim-so-hyun-1.jpg")
		val image: Call<ResponseBody>
		
//		@get:GET("movie/now_playing")
//		val nowPlaying: Observable<Movies>
//
//		@GET("search/movie")
//		fun getSearch(@Query("query") search: String): Observable<Movies>
//
//		@GET("movie/{id}")
//		fun getDetailMovie(@Path("id") id: Int): Observable<Movie>
}
