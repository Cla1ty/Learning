package com.dwiariyanto.recyclerview

import android.widget.TextView
import com.dwiariyanto.recyclerview.adapter.BaseItemView
import com.dwiariyanto.recyclerview.adapter.RecyclerViewHolder

class Row : BaseItemView<Model>(
        Model::class.java,
        R.layout.row_text
)
{
    var counter = 0
    override fun onBind(holder: RecyclerViewHolder, data: Model)
    {
        counter++
        holder.tag = holder.layoutPosition
        (holder.itemView as TextView).text = "${holder.layoutPosition}, ${holder.adapterPosition} : ${counter}"
    }
}
