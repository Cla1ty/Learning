package com.dwiariyanto.glide.OkHttp3

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import java.io.InputStream

 class OkHttpLibraryGlideModule : AppGlideModule() {
	override fun registerComponents(
			context: Context,
			glide: Glide,
			registry: Registry
	) {
		registry.replace(
				GlideUrl::class.java,
				InputStream::class.java,
				OkHttpUrlLoader.Factory()
		)
	}
	
	 override fun isManifestParsingEnabled(): Boolean {
		 return false
	 }
 }
