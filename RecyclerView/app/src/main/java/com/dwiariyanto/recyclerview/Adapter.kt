package com.dwiariyanto.recyclerview

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.dwiariyanto.recyclerview.adapter.BaseRecyclerViewAdapter

class Adapter() : BaseRecyclerViewAdapter(Row())
{
    override fun build(recyclerView: RecyclerView)
    {
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
        }
    }
}