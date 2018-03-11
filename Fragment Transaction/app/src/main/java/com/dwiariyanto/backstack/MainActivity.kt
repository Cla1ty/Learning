package com.dwiariyanto.backstack

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dwiariyanto.backstack.extension.getLastFragmentVisibleOrNull
import com.dwiariyanto.backstack.extension.traceBackStack
import com.dwiariyanto.backstack.extension.transact
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transact { add(R.id.content, BlankFragment(), "First") }
    }

    fun textTrace()
    {
        text.postDelayed({
            getLastFragmentVisibleOrNull()
                    ?.tag
                    ?.also { text.text = traceBackStack(it) }
        }, 100)
    }
}