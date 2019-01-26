package com.dwiariyanto.collapsingtoolbarlayout

import android.view.View
import com.dwiariyanto.recyclerview.adapter.BaseItemView
import com.dwiariyanto.recyclerview.adapter.RecyclerViewHolder
import kotlinx.android.synthetic.main.item_recycler.view.*

class ItemView : BaseItemView<ViewModel>(
        ViewModel::class.java,
        R.layout.item_recycler
)
{
    var onClick: ((viewTransition: View, viewModel: ViewModel) -> Unit)? = null

    override fun onCreate(holder: RecyclerViewHolder)
    {

        holder.itemView.setOnClickListener {
            val viewModel = it.tag as ViewModel
            onClick?.invoke(it.image, viewModel)
        }
    }

    override fun onBind(holder: RecyclerViewHolder, data: ViewModel)
    {
        holder.tag = data
        holder.itemView.apply {
            text.text = data.text
            image.setImageResource(data.image)

            tag = data
        }
    }
}