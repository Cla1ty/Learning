package com.dwiariyanto.glide

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object NetworkModule {
	fun provideRetrofit(): Retrofit {
		val interceptor = Interceptor {
			val url = it.request()
					.url()
					.newBuilder()
					.build()
			
			val request = it.request()
					.newBuilder()
					.url(url)
					.build()
			
			it.proceed(request)
		}
		
		val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
		
		val client = OkHttpClient.Builder()
				.addInterceptor(logger)
				.addInterceptor(interceptor)
				.build()
		
		return Retrofit.Builder()
				.baseUrl("https://0.soompi.io/")
//				.addConverterFactory(GsonConverterFactory.create())
//				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.client(client)
				.build()
	}
	
	fun provideMovies(retrofit: Retrofit): ImageApi = retrofit.create(ImageApi::class.java)
}
