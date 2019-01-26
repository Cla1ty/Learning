package com.dwiariyanto.recyclerviewcascadingeffect

import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class Adapter : RecyclerView.Adapter<ViewHolder>()
{
    private var data = ArrayList<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = View.inflate(parent.context, R.layout.item, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int
    {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        (holder.itemView as TextView).text = data[position]
    }

    var newData = ArrayList<String>()
    fun updateData(data: ArrayList<String>)
    {
        this.data.clear()
        this.newData.clear()
        notifyDataSetChanged()

        newData = data
        currentPosition = 0
        animate()
    }

    private var currentPosition: Int = 0
    private val handler = Handler()
    private val runnable = Runnable {
        onAnimate()
        animate()
    }

    private fun onAnimate()
    {
        Log.d("TAG", currentPosition.toString())
        data.add(newData[currentPosition])
        notifyItemInserted(currentPosition++)
    }

    private fun animate()
    {
        if (currentPosition < newData.size)
            handler.postDelayed(runnable, 100)
    }

}
