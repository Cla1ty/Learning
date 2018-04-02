package com.dwiariyanto.recyclerview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dwiariyanto.recyclerview.adapter.RecyclerViewHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    private val adapter = Adapter()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = arrayListOf<Model>()
        (0 until 20).forEach {
            data.add(Model(it))
        }

        recyclerView.adapter = adapter
        adapter.data = data

        buttonApply()
        buttonNotifyDataSetChange()
    }

    private fun buttonNotifyDataSetChange()
    {
        btnDataSetChange.setOnClickListener { adapter.notifyDataSetChanged() }
    }

    private fun buttonApply()
    {
        btnApply.setOnClickListener { notif() }
    }

    private fun notif()
    {
        val listViewHolder = (0 until recyclerView.childCount)
                .map { recyclerView.getChildAt(it) }
                .map { recyclerView.getChildViewHolder(it) as RecyclerViewHolder }

        val selectedViewHolder = listViewHolder
                .firstOrNull { it.tag.toString() == etIndex.text.toString() }

        selectedViewHolder?.also {
            adapter.notifyItemChanged(it.layoutPosition)
        }

    }
}
