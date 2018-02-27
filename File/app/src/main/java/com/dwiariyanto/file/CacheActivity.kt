package com.dwiariyanto.file

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.io.File
import java.io.FileOutputStream

class CacheActivity : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_cache)
		
		val file = File(
				cacheDir,
				"childDir/child"
		)
		
		val dir = File(
				cacheDir,
				"childDir/"
		)
		
		Log.d(
				"TAG",
				"IS EXISTS " + file.exists()
		)
		
		Log.d(
				"TAG",
				"MAKE DIR" + dir.mkdirs()
		)
		
		val stream = FileOutputStream(file)
		stream.write("TEST".toByteArray())
		stream.close()
		
		
		Log.d(
				"TAG",
				"IS EXISTS " + file.exists()
		)
		
		dir.listFiles()
				.forEach {
					Log.d(
							"TAG",
							"DEL " + it.delete()
					)
				}
		
		Log.d(
				"TAG",
				"IS EXISTS " + file.exists()
		)
	}
}
