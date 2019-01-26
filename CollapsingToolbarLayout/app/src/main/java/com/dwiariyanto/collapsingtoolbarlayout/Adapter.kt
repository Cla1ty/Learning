package com.dwiariyanto.collapsingtoolbarlayout

import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import com.dwiariyanto.recyclerview.adapter.BaseRecyclerViewAdapter
import com.dwiariyanto.recyclerview.extension.erViDecor
import com.dwiariyanto.recyclerview.extension.erViManager

class Adapter(
        val itemView: ItemView = ItemView()
) : BaseRecyclerViewAdapter(
        itemView
)
{
    override fun build(recyclerView: RecyclerView)
    {
        recyclerView.erViManager {
            erViCount = 2
        }
        recyclerView.erViDecor {
            erViSpanSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    16f,
                    recyclerView.context.resources.displayMetrics).toInt()
        }
    }
}
