package com.dwiariyanto.recyclerviewcascadingeffect

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    private var isLoaded = false
    private val adapter = Adapter()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonLoad()
        buttonClear()
    }

    private fun buttonClear()
    {
        val data = ArrayList<String>()
        btn2.setOnClickListener { adapter.updateData(data)}
    }

    private fun buttonLoad()
    {
        btn1.setOnClickListener { performLoad() }
    }

    private fun performLoad()
    {
        if(isLoaded) return
        isLoaded = true

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<String>()
        (0 until 10).forEach { data.add("Counter $it") }
        adapter.updateData(data)
    }
}
