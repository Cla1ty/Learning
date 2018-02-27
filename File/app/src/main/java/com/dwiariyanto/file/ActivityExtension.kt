package com.dwiariyanto.file

import android.content.Intent
import android.support.v4.app.FragmentActivity

fun FragmentActivity.goto(clz: Class<*>) {
	startActivity(
			Intent(
					baseContext,
					clz
			)
	)
}