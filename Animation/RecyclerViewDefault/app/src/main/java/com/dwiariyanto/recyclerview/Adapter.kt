package com.dwiariyanto.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView

class Adapter : RecyclerView.Adapter<ViewHolder>()
{
    var data = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val image = TextView(parent.context)
        image.setBackgroundResource(R.drawable.ic_launcher_background)
        return ViewHolder(image)
    }

    override fun getItemCount(): Int
    {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        (holder.itemView as TextView).text = data[position]
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>)
    {
        if (payloads.isNotEmpty())
            (holder.itemView as TextView).text = payloads[0].toString()
        else
            onBindViewHolder(holder, position)
    }

    private lateinit var text: () -> String
    fun positionText(text: () -> String)
    {
        this.text = text
    }

    private val position: Int
        get()
        {
            if (text.invoke().isBlank()) return 0

            var curPosition = text.invoke().toInt()
            if (curPosition >= itemCount) curPosition = itemCount - 1
            return curPosition
        }

    private fun counterText(): String = "Counter ${++counter}"

    private var counter = 0

    fun insert()
    {
        data.add(counterText())
        notifyItemInserted(position)
    }

    fun remove()
    {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    fun itemChange()
    {
        data[position] = counterText()
        notifyItemChanged(position)
    }

    fun itemChangePayload()
    {
        notifyItemChanged(position, counterText())
    }

    fun moveTop()
    {
        if (this.position == 0) return

        notifyItemMoved(position, position - 1)
    }

    fun moveBottom()
    {
        if (this.position >= itemCount - 1) return

        notifyItemMoved(position, position + 1)
    }
}