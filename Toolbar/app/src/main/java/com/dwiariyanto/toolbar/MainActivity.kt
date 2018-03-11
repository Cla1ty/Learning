package com.dwiariyanto.toolbar

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setOnClickListener {
            Intent(this, ToolbarActivity::class.java).also {
                startActivity(it)
            }
        }
        btn2.setOnClickListener {
            Intent(this, FragmentActivity::class.java).also {
                startActivity(it)
            }
        }

    }
}
