package com.dwiariyanto.recyclerview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    private val adapter: Adapter by lazy { Adapter() }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        setupButton()
    }

    private fun setupRecyclerView()
    {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter.positionText { position.text.toString() }
    }

    private fun setupButton()
    {
        buttonInsert()
        buttonRemove()
        buttonMoveTop()
        buttonMoveBottom()
        buttonChange()
        buttonChangePayload()
    }

    private fun buttonChangePayload()
    {
        btn6.setOnClickListener { adapter.itemChangePayload() }
    }

    private fun buttonChange()
    {
        btn5.setOnClickListener { adapter.itemChange() }
    }

    private fun buttonMoveBottom()
    {
        btn4.setOnClickListener { adapter.moveBottom() }
    }

    private fun buttonMoveTop()
    {
        btn3.setOnClickListener { adapter.moveTop() }
    }

    private fun buttonRemove()
    {
        btn2.setOnClickListener { adapter.remove() }
    }

    private fun buttonInsert()
    {
        btn1.setOnClickListener { adapter.insert() }
    }
}