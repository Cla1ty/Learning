package com.dwiariyanto.readdatabasefromassets

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbAssets = DbAssets(this, "kbbi.db", 1)
        dbAssets.writableDatabase.run {
            val cursor = rawQuery("SELECT * FROM kbbi WHERE kata LIKE '%aba%'", null)
            cursor.moveToFirst()
            if (cursor.count > 0) {
                Log.d("Trace", cursor.getString(0))
                while (!cursor.isAfterLast) {
                    Log.d("Trace", cursor.getString(0))
                    cursor.moveToNext()
                }
            }
            cursor.close()
            close()
        }
    }
}
