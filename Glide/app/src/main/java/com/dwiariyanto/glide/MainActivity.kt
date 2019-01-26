package com.dwiariyanto.glide

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dwiariyanto.glide.OkHttp3.OkHttpUrlLoader
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		
		
		val client = OkHttpClient()
		val request = Request.Builder()
				.url("https://0.soompi.io/wp-content/uploads/2017/07/13225736/kim-so-hyun-1.jpg")
				.build();
		
		
		val oh = OkHttpUrlLoader(client)

		Glide.with(this)
				.asDrawable()
				.load(request)
				.into(imageView)
		
//		Glide.with(this)
//				.load(NetworkModule.provideMovies(NetworkModule.provideRetrofit()))
//				.into(imageView)
		
//		GlideApp.with(this).load("http://goo.gl/gEgYUd").into(imageView);
	}
}
